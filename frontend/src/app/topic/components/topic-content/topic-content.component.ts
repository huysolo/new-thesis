import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Topic } from '../../../models/Topic';
import { TopicService } from '../../topic.service';
import { AuthService } from '../../../core/auth.service';
import { MatDialog } from '@angular/material';
import { TopicDetail } from '../../../models/TopicDetail';
import { TopidDetailComponent } from '../topid-detail/topid-detail.component';

@Component({
  selector: 'app-topic-content',
  templateUrl: './topic-content.component.html',
  styleUrls: ['./topic-content.component.css']
})
export class TopicContentComponent implements OnInit {
  @Input() topic: Topic;
  @Input() semno;
  @Output('editTopic') editTopic = new EventEmitter<Topic>();
  @Output('delTopic') delTopic = new EventEmitter<Number>();
  @Output('applyTopic') applyTopic = new EventEmitter<Topic>();
  @Output() removeTopic = new EventEmitter<Number>();

  constructor(public topicSv: TopicService, public authoSv: AuthService) { }

  ngOnInit() {
  }

  apply() {
    this.topicSv.applyToTopic(this.topic.idTop).subscribe(data => {
      this.applyTopic.emit(data);
    }, err => {
      console.log(err);
    });
  }
  reject() {
    this.topicSv.reject(this.topicSv.appliedTopic.idTop).subscribe(data => {
      this.removeTopic.emit(data);
    });
  }

  publish() {
    this.topicSv.publishTopic(this.topic.idTop).subscribe(data => {
      this.removeTopic.emit(data.idTop);
    });
  }

  edit() {
    this.editTopic.emit(this.topic);
  }

  delete() {
    this.delTopic.emit(this.topic.idTop);
  }
}
