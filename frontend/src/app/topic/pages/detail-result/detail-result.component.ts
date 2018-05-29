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
import { StudentDoTask } from '../../../task/components/student-do-task';
import { Observable } from 'rxjs/Observable';
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-detail-result',
  templateUrl: './detail-result.component.html',
  styleUrls: ['./detail-result.component.css']
})
export class DetailResultComponent implements OnInit {
  listReviewSrc: MatTableDataSource<Review>;
  topicRvStandardDetail: TopicSemStandard[];
  curStudent: StudentDoTask = null;
  listColReviewFullWithRole = [
    'idProf',
    'role',
    'score',
    'action'
  ];
  listColReviewFull = [
    'idProf',
    'score',
    'action'
  ];
  topic: TopicDetail;
  students: StudentDoTask[];
  constructor(public authSv: AuthService, public router: Router, private layoutSv: LayoutService,
    public topicSv: TopicService, public route: ActivatedRoute, public standardSv: StandardService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.layoutSv.labelName = 'Topic Detail';

      this.topicSv.getTopicDetail(params['id']).subscribe(data => {
        this.topic = data;
      });
      this.topicSv.getAllStudentDoTopic(params['id']).subscribe(data => {
        this.curStudent = data.find(dt => dt.userId == this.authSv.getUserId());

        this.students = data;
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

  detailCouncil(idCouncil: Number) {
    this.standardSv.getReviewCouncil(this.topic.topic.idTop, idCouncil).subscribe(data => {
      this.topicRvStandardDetail = data;
    });
  }

  setTeamlead() {
    this.topicSv.setTeamlead(this.topic.topic.idTop).subscribe(data => {
      for (let index = 0; index < this.students.length; index++) {
        if (this.students[index].userId != this.authSv.getUserId()) {
          this.students[index].teamlead = false;
        } else {
          this.students[index].teamlead = true;
          this.curStudent.teamlead = true;
        }
      }
    });
  }

}
