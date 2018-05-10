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
import { TopicSemStandard } from '../../../models/TopicSemStandard';

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
  standdardSelected: Standard;
  topicSelected: Topic;
  topicRvStandardDetail: TopicSemStandard[];
  ngOnInit() {
    this.topicRvStandardDetail = new Array<TopicSemStandard>();
    this.standdardSelected = new Standard();
    this.standardCreate = new Standard();
    this.standardList = this.standardSv.getCurrentSemStandard();
    this.topicReviewList = this.topicSv.getListReview(new HttpParams().set('submitted', '0'));

  }

  reset() {
    this.standdardSelected = new Standard();
  }

  addStandard() {
    this.standardCreate.idUser = parseInt(this.authoSv.getUserId());
    this.standardSv.postStandard(this.standardCreate).subscribe(data => {
      this.standardList = this.standardList.map(st => {
        return st;
      });
    });
  }

  editStandard(standard: Standard) {
    this.standdardSelected = Object.assign({}, standard);
  }
  submitEdit() {
    this.standardSv.postStandard(this.standdardSelected).subscribe(data => {
      this.standardList = this.standardList.map(st => {
        return st;
      });
      this.reset();
    });
  }

  review(topicId: Number) {
    this.standardSv.getListStandardAndGeneral().subscribe(stLst => {
      this.reviewTp = new ReviewTopic;
      this.reviewTp.topicId = topicId;
      this.standardListReview = stLst;
      this.reviewTp.standardScores = new Array<StandardScore>();
      this.standardListReview.forEach(s => {
        this.reviewTp.standardScores.push(new StandardScore(s.idStandard));
      });
    });
  }
  submitReview() {
    this.standardSv.postReview(this.reviewTp).subscribe(data => {
      this.topicReviewList = this.topicReviewList.map(tplst => {
        return tplst.filter(topic => topic.idTop != data.idTopic);
      });
    });
  }

  removeStandard(standardId: Number) {
    this.standardSv.deleteStandard(standardId).subscribe(data => {
      this.standardList = this.standardList.map(lst => {
        return lst.filter(st => st.idStandard != data);
      });
    });
  }

  detail(topicId: Number) {
    this.standardSv.getReview(topicId).subscribe(data => {
      this.topicRvStandardDetail = data;
    });
  }

  setSlTopic(tp: Topic) {
    this.topicSelected = tp;
  }

  onLinkClick(e) {
    if (e.tab.textLabel === 'In Review') {
      this.topicReviewList = this.topicSv.getListReview(new HttpParams().set('submitted', '0'));
    } else {
      this.topicReviewedList = this.topicSv.getListReview(new HttpParams().set('submitted  ', '1'));
    }
  }


}
