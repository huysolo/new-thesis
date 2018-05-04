import { Component, OnInit } from '@angular/core';
import { StandardService } from '../../../standard.service';
import { Observable } from 'rxjs/Observable';
import { Standard } from '../../../models/Standard';
import { Topic } from '../../../models/Topic';
import { TopicService } from '../../../topic/topic.service';
import { HttpParams } from '@angular/common/http';
import { AuthService } from '../../../core/auth.service';
import { ReviewTopic } from '../../../models/ReviewTopic';
import { StandardScore } from '../../../models/StandardScore';
import { TopicReview } from '../../../models/TopicReview';

@Component({
  selector: 'app-manage-standard',
  templateUrl: './manage-standard.component.html',
  styleUrls: ['./manage-standard.component.css']
})
export class ManageStandardComponent implements OnInit {

  constructor(public standardSv: StandardService, public topicSv: TopicService, public authoSv: AuthService) { }
  standardList: Observable<Standard[]>;
  standardListReview: Standard[];
  topicReviewList: Observable<Topic[]>;
  topicReviewedList: Observable<Topic[]>;
  standardCreate: Standard;
  reviewTp: ReviewTopic;
  idStandard = -1;
  ngOnInit() {
    this.standardCreate = new Standard();
    this.standardList = this.standardSv.getCurrentSemStandard();
    this.topicReviewList = this.topicSv.getListReview(new HttpParams().set('submitted', '0'));
    this.topicReviewedList = this.topicSv.getListReview(new HttpParams().set('submitted', '1'));
  }

  addStandard() {
    this.standardCreate.idUser = parseInt(this.authoSv.getUserId());
    this.standardSv.postStandard(this.standardCreate).subscribe(data => {
      console.log(data);
      this.standardList = this.standardList.map(st => {
        return st;
      });
    });
  }

  review(topicId: Number) {

    this.standardList.subscribe(st => {
      this.reviewTp = new ReviewTopic;
      this.reviewTp.topicId = topicId;
      this.standardListReview = st;
      this.reviewTp.standardScores = new Array<StandardScore>();
      st.forEach(s => {
        this.reviewTp.standardScores.push(new StandardScore(s.idStandard));
      });
    });
  }
  submitReview() {
      console.log(this.reviewTp.standardScores[0].score);
      this.standardSv.postReview(this.reviewTp).subscribe(data => {
        console.log(data);
      });
  }

  removeStandard(standardId: Number) {
    this.standardSv.deleteStandard(standardId).subscribe(data => {
      this.standardList = this.standardList.map(lst => {
        return lst.filter(st => st.idStandard != data);
      });
    });
  }

}
