import { Component, OnInit, ViewChild } from '@angular/core';
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
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-manage-standard',
  templateUrl: './manage-standard.component.html',
  styleUrls: ['./manage-standard.component.css']
})
export class ManageStandardComponent implements OnInit {

  constructor(public standardSv: StandardService, public topicSv: TopicService, public authoSv: AuthService) { }
  standardList: Observable<Standard[]>;
  standardListReview: Standard[];
  standardCreate: Standard;
  standdardSelected: Standard;
  standardSrc: MatTableDataSource<Standard>;
  @ViewChild('paginator') paginator: MatPaginator;
  ngOnInit() {
    this.standdardSelected = new Standard();
    this.standardCreate = new Standard();
    this.standardList = this.standardSv.getCurrentSemStandard();
    // this.standardSv.getCurrentSemStandard().subscribe(data => {
    //   this.standardSrc = new MatTableDataSource(data);
    // });

  }

  reset() {
    this.standdardSelected = new Standard();
  }

  addStandard() {
    this.standardCreate.idUser = parseInt(this.authoSv.getUserId());
    this.standardSv.postStandard(this.standardCreate).subscribe(data => {
      // this.standardSrc.data.push(data);
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

  removeStandard(standardId: Number) {
    this.standardSv.deleteStandard(standardId).subscribe(data => {
      this.standardList = this.standardList.map(lst => {
        return lst.filter(st => st.idStandard != data);
      });
    });
  }



}
