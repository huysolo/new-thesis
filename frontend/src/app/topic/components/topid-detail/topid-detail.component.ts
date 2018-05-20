import { Component, OnInit, Input, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TopicContentComponent } from '../topic-content/topic-content.component';
import { TopicDetail } from '../../../models/TopicDetail';
import { Observer } from 'rxjs/Observer';
import { TopicService } from '../../topic.service';
import { TopicDetailDirective } from '../topic-detail.directive';
import { CommonService } from '../../../core/common.service';
import { Observable } from 'rxjs/Observable';
import { StudentDoTask } from '../../../task/components/student-do-task';

@Component({
  selector: 'app-topid-detail',
  templateUrl: './topid-detail.component.html',
  styleUrls: ['./topid-detail.component.css']
})
export class TopidDetailComponent implements OnInit {
  topicDetail: TopicDetail;
  specName: Observable<String>;
  students: Observable<StudentDoTask[]>;
  constructor(public dialogRef: MatDialogRef<TopicDetailDirective>,
    @Inject(MAT_DIALOG_DATA) public data: any, public topicSv: TopicService, public commonSv: CommonService) {
        this.topicDetail = data['topicDetail'];
        this.specName = commonSv.getSpecNameById(this.topicDetail.topic.idSpecialize);
        this.students = topicSv.getAllStudentDoTopic(this.topicDetail.topic.idTop);
    }

  ngOnInit() {

  }

}
