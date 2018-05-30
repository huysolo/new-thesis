import { Component, OnInit, NgZone, ViewChild } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { AuthService } from '../../../core/auth.service';
import { Observable } from 'rxjs/Observable';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { SemesterService } from '../../../core/semester.service';
import { Review } from '../../../models/Review';
import { ActivatedRoute, Router } from '@angular/router';
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-result-topic',
  templateUrl: './result-topic.component.html',
  styleUrls: ['./result-topic.component.css']
})
export class ResultTopicComponent implements OnInit {
  listTopicSrc: MatTableDataSource<Topic>;
  listDraftSrc: MatTableDataSource<Topic>;
  listReview: Review[];
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('paginatorDraft') paginatorDraft: MatPaginator;

  constructor(public route: Router, private layoutSv: LayoutService,
    public topicSv: TopicService, public authSv: AuthService, private zone: NgZone, public semSv: SemesterService) {
      layoutSv.labelName = 'Overview';

    }

  ngOnInit() {

    this.zone.run(() => {
      if (this.authSv.isStudent()) {
        this.topicSv.getAppliedList().subscribe(data => {
          this.listTopicSrc =  new MatTableDataSource(data);
          this.listTopicSrc.paginator = this.paginator;
        });
      } else {
        this.topicSv.getListTopicForProf().subscribe(data => {
          this.listTopicSrc =  new MatTableDataSource(data);
          this.listTopicSrc.paginator = this.paginator;
        });
        this.topicSv.getListDraft().subscribe(data => {
          this.listDraftSrc = new MatTableDataSource(data);
          this.listDraftSrc.paginator = this.paginatorDraft;
        });
      }
    });


  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.listTopicSrc.filter = filterValue;
  }

  changeSem(semNo) {
    this.topicSv.getListTopicForProf(semNo).subscribe(data => {
      this.listTopicSrc =  new MatTableDataSource(data);
      this.listTopicSrc.paginator = this.paginator;
    });
  }

  addTopic(topic, draft = false) {
    if (topic !== undefined) {
      let listTopic = new Array<Topic>();
      if (!draft) {
        listTopic = this.listTopicSrc.data;
        listTopic.unshift(topic);
        this.listTopicSrc.data =  listTopic;
      } else {
        listTopic = this.listDraftSrc.data;
        listTopic.unshift(topic);
        this.listDraftSrc.data =  listTopic;
      }
    }
  }


}
