import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { ActivatedRoute } from '@angular/router';
import { Review } from '../../../models/Review';
import { MatTableDataSource } from '@angular/material';
import { StandardService } from '../../../standard.service';
import { TopicSemStandard } from '../../../models/TopicSemStandard';

@Component({
  selector: 'app-detail-result',
  templateUrl: './detail-result.component.html',
  styleUrls: ['./detail-result.component.css']
})
export class DetailResultComponent implements OnInit {
  listReviewSrc: MatTableDataSource<Review>;
  topicRvStandardDetail: TopicSemStandard[];
  idTopic;
  listColReviewFull = [
    'idTopic',
    'idProf',
    'score',
    'action'
    // 'submitted',
    // 'idReview',
  ];
  constructor(public topicSv: TopicService, public route: ActivatedRoute, public standardSv: StandardService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.idTopic = params['id'];
      this.topicRvStandardDetail = null;
      this.topicSv.getReviewsByIdTopic(params['id']).subscribe(data => {
        this.listReviewSrc = new MatTableDataSource(data);
      });
    });
  }

  detail(profId: Number) {
    this.standardSv.getReview(this.idTopic, profId).subscribe(data => {
      this.topicRvStandardDetail = data;
    });
  }

}
