import { Component, OnInit, NgZone, ViewChild } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { AuthService } from '../../../core/auth.service';
import { Observable } from 'rxjs/Observable';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { SemesterService } from '../../../core/semester.service';
import { Review } from '../../../models/Review';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-result-topic',
  templateUrl: './result-topic.component.html',
  styleUrls: ['./result-topic.component.css']
})
export class ResultTopicComponent implements OnInit {
  listTopicSrc: MatTableDataSource<Topic>;
  listReview: Review[];
  @ViewChild('paginator') paginator: MatPaginator;
  constructor(public route: Router,
    public topicSv: TopicService, public authSv: AuthService, private zone: NgZone, public semSv: SemesterService) { }

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


}
