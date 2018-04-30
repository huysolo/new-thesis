import { Component, OnInit, Input, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TopicContentComponent } from '../topic-content/topic-content.component';
import { TopicDetail } from '../../../models/TopicDetail';
import { Observer } from 'rxjs/Observer';
import { TopicService } from '../../topic.service';

@Component({
  selector: 'app-topid-detail',
  templateUrl: './topid-detail.component.html',
  styleUrls: ['./topid-detail.component.css']
})
export class TopidDetailComponent implements OnInit {
  topicDetail: TopicDetail;
  constructor(public dialogRef: MatDialogRef<TopicContentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public topicSv: TopicService) {
        this.topicDetail = data['topicDetail'];

    }

  ngOnInit() {

  }

}
