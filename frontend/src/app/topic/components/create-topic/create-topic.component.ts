import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TopicDetail } from '../../../models/TopicDetail';
import { Topic } from '../../../models/Topic';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { TopicMission } from '../../../models/TopicMission';
import { AuthService } from '../../../core/auth.service';
import { TopicService } from '../../topic.service';
import { Observable } from 'rxjs/Observable';
import { Specialize } from '../../../models/Specialize';
import { CommonService } from '../../../core/common.service';

@Component({
  selector: 'app-create-topic',
  templateUrl: './create-topic.component.html',
  styleUrls: ['./create-topic.component.css']
})
export class CreateTopicComponent implements OnInit {
  constructor(public authoSv: AuthService, public topicSv: TopicService, private commonSv: CommonService) { }
  @Input() createTopic: TopicDetail = new TopicDetail();
  specLst: Observable<Specialize[]>;
  @Output('created') created = new EventEmitter<Boolean>();
  ngOnInit() {
    this.specLst = this.commonSv.getListSpec();
  }

  submitTopic(draft: boolean) {
    this.createTopic.topic.publishDate = null;
    this.createTopic.draft = draft;
    this.topicSv.createTopic(this.createTopic).subscribe(data => {
        this.created.emit(true);
    }, (err) => {
      console.log(err);
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
    mission.idTopic = 0;
    this.createTopic.topicMission.push(new TopicMission());
  }

  removeMission(removePos: number) {
    this.createTopic.topicMission.splice(removePos, 1);
  }

}
