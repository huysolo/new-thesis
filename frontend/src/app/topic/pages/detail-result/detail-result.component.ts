import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../../../models/Review';
import { MatTableDataSource } from '@angular/material';
import { StandardService } from '../../../standard.service';
import { TopicSemStandard } from '../../../models/TopicSemStandard';
import { AuthService } from '../../../core/auth.service';
import { Topic } from '../../../models/Topic';
import { TopicDetail } from '../../../models/TopicDetail';

@Component({
  selector: 'app-detail-result',
  templateUrl: './detail-result.component.html',
  styleUrls: ['./detail-result.component.css']
})
export class DetailResultComponent implements OnInit {
  listReviewSrc: MatTableDataSource<Review>;
  topicRvStandardDetail: TopicSemStandard[];
  listColReviewFull = [
    'idTopic',
    'idProf',
    'score',
    'action'
  ];
  topic: TopicDetail;
  constructor(public authSv: AuthService, public router: Router,
    public topicSv: TopicService, public route: ActivatedRoute, public standardSv: StandardService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.topicSv.getTopicDetail(params['id']).subscribe(data => {
        this.topic = data;
      });
      this.topicRvStandardDetail = null;
      this.topicSv.getReviewsByIdTopic(params['id']).subscribe(data => {
        this.listReviewSrc = new MatTableDataSource(data);
      });
    });
  }

  detail(profId: Number) {
    this.standardSv.getReview(this.topic.topic.idTop, profId).subscribe(data => {
      this.topicRvStandardDetail = data;
    });
  }

}
