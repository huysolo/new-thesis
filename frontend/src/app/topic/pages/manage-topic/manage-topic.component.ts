import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../../core/common.service';
import { Semester } from '../../../models/Semester';
import { ProfInfo } from '../../../models/ProfInfo';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-manage-topic',
  templateUrl: './manage-topic.component.html',
  styleUrls: ['./manage-topic.component.css']
})
export class ManageTopicComponent implements OnInit {
  // public listSem: Observable<Semester[]>;
  // public topicLst: Observable<Topic[]>;
  // public profLst: Observable<ProfInfo[]>;

  // public selectedSem;
  // public selectedProfId;
  constructor(public topicSv: TopicService, public commonSv: CommonService, public authoSv: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
  }



}
