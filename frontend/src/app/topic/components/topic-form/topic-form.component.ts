import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../core/auth.service';

import { Input, Output, EventEmitter } from '@angular/core';
import { TopicDetail } from '../../../models/TopicDetail';
import { Topic } from '../../../models/Topic';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { TopicMission } from '../../../models/TopicMission';
import { TopicService } from '../../topic.service';
import { Observable } from 'rxjs/Observable';
import { Specialize } from '../../../models/Specialize';
import { CommonService } from '../../../core/common.service';
import { Validators, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-topic-form',
  templateUrl: './topic-form.component.html',
  styleUrls: ['./topic-form.component.css']
})
export class TopicFormComponent implements OnInit {

  constructor(public authoSv: AuthService,
    public topicSv: TopicService, private commonSv: CommonService,
    private snackbar: MatSnackBar
  ) { }
  @Input() createTopic: TopicDetail = new TopicDetail();
  @Input() draft = false;
  specLst: Observable<Specialize[]>;
  @Output('created') created = new EventEmitter<Topic>();

  numberForm = new FormControl('', [
    Validators.required,
    Validators.pattern('^((\\+91-?)|0)?[0-9]{1}$')
  ]);
  ngOnInit() {
    this.specLst = this.commonSv.getListSpec();
  }

  submitTopic() {
    this.createTopic.topic.publishDate = null;
    this.createTopic.draft = this.draft;
    this.topicSv.createTopic(this.createTopic).subscribe(data => {
        this.created.emit(data);
        this.snackbar.open(this.createTopic.topic.idTop ? 'Update Topic Success' : 'Create Topic Success', 'Close', {
          duration: 2000,
        });
    }, (err) => {
      this.snackbar.open(err, 'Close', {
        duration: 4000,
      });
    }, );
  }

  addReq() {
    const req = new TopicRequirement();
    this.createTopic.topicRequirement.push(new TopicRequirement());
  }

  removeReq(removePos: number) {
    this.createTopic.topicRequirement.splice(removePos, 1);
  }

  addMission() {
    const mission = new TopicMission();
    this.createTopic.topicMission.push(new TopicMission());
  }

  removeMission(removePos: number) {
    this.createTopic.topicMission.splice(removePos, 1);
  }
}
