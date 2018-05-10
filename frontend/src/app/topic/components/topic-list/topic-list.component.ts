import { Component, OnInit, NgZone } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Topic } from '../../../models/Topic';
import { Semester } from '../../../models/Semester';
import { TopicService } from '../../topic.service';
import { CommonService } from '../../../core/common.service';
import { ProfInfo } from '../../../models/ProfInfo';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { TopicDetail } from '../../../models/TopicDetail';
import { TopicMission } from '../../../models/TopicMission';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { HttpParams } from '@angular/common/http';
import { Specialize } from '../../../models/Specialize';
import { MatSnackBar } from '@angular/material';

declare var $: any;
@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  public listSem: Observable<Semester[]>;
  public profLst: Observable<ProfInfo[]>;
  public specLst: Observable<Specialize[]>;

  public selectedSem = 0;
  public selectedProfId = 0;
  public selectedSpecId = 0;

  modalLabel = 'New';

  public topicDetail = new TopicDetail();
  title: String = 'New';
  p = 1;

  constructor(public topicSv: TopicService, private zone: NgZone,
    public commonSv: CommonService, public authoSv: AuthService, private route: ActivatedRoute,  public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.selectedProfId = this.authoSv.isProfessor() ? this.authoSv.getProfID() : null;
    this.route.params.subscribe(params => {
      this.modalLabel = 'New';

      this.selectedSem = 0;
      this.selectedProfId = 0;
      this.selectedSpecId = 0;

      this.listSem = this.commonSv.getListSemester();
      this.profLst = this.commonSv.getListProf();
      this.specLst = this.commonSv.getListSpec();
      this.topicSv.requestType = params['typ'];
      this.topicSv.appliedTopic = null;

      if (params['typ'] === 'recent') {
        this.getAppliedTopic(0);
      }

      this.getWithRarams();
    });
  }

  getWithRarams() {
    this.zone.run(() => {
      let params = new HttpParams();
      if (this.selectedSem != 0) {
        params = params.set('semno', this.selectedSem.toString());
      }

      if (this.selectedProfId != 0) {
        params = params.set('profId', this.selectedProfId.toString());
      }
      if (this.selectedSpecId != 0) {
        params = params.set('spec', this.selectedSpecId.toString());
      }

      this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(params);
    });

  }

  onChangeSemester(sem) {
    this.selectedSem = sem;
    this.getWithRarams();
    this.getAppliedTopic(this.selectedSem);
  }

  onChangeProf(prof) {
    this.selectedProfId = prof;
    this.getWithRarams();
  }

  onChangeSpec(spec) {
    this.selectedSpecId = spec;
    this.getWithRarams();
  }

  getAppliedTopic(selectedSem: Number) {
    let params = new HttpParams();
    if (this  .selectedSem != 0) {
      params = params.set('semno', selectedSem.toString());
    }

    this.topicSv.getAppliedTopic(params).subscribe(topic => {
      this.topicSv.appliedTopic = topic;
    });
  }

  validTopic(topicId: number) {
    return this.topicSv.appliedTopic == null || topicId !== this.topicSv.appliedTopic.idTop;
  }


  onEdit(event) {
    this.modalLabel = 'Edit';
    this.topicSv.getTopicDetail(event).subscribe(data => {
      this.topicDetail = data;
      $('#createTopic').modal('show');
    });
  }

  onDelete(event) {
    this.topicSv.deleteTopic(event).subscribe(data => {
      this.topicSv.topicLst = this.topicSv.topicLst.map(topics => {
        return topics.filter(top => top.idTop != event);
      });
    });
  }

  onApply(event: Topic) {
    this.topicSv.topicLst = this.topicSv.topicLst.map(topicLst => {
      this.topicSv.appliedTopic = event;
      return topicLst.filter(top => {
        return top != null && top.idTop !== this.topicSv.appliedTopic.idTop;
      });
    });
  }

  newTopic() {
    this.modalLabel = 'New';
    this.topicDetail = new TopicDetail();
    this.topicDetail.topic.idProf = this.authoSv.getProfID();
    this.topicDetail.topicMission.push(new TopicMission());
    this.topicDetail.topicRequirement.push(new TopicRequirement());
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
