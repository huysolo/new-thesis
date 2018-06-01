import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
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
import { SemesterService } from '../../../core/semester.service';
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-manage-standard',
  templateUrl: './manage-standard.component.html',
  styleUrls: ['./manage-standard.component.css']
})
export class ManageStandardComponent implements OnInit, AfterViewInit {

  constructor(public standardSv: StandardService, private layoutSv: LayoutService,
    public topicSv: TopicService, public authoSv: AuthService,
    public semesterSv: SemesterService) {
      layoutSv.labelName = 'Criterion';
    }
  standardList: Observable<Standard[]>;
  standardListReview: Standard[];
  standardCreate: Standard;
  standdardSelected: Standard;
  standardSrc: MatTableDataSource<Standard>;
  standardGeneral: MatTableDataSource<Standard>;
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('paginatorGeneral') paginatorGeneral: MatPaginator;

  displayedColumns = ['idStandard', 'stName', 'coefficient', 'action'];
  displayedColumnsGeneral = ['idStandard', 'stName', 'coefficient', 'semester'];
  ngOnInit() {
    this.standdardSelected = new Standard();
    this.standardCreate = new Standard();
    // this.standardList = this.standardSv.getCurrentSemStandard();
    this.standardSv.getCurrentSemStandard().subscribe(data => {
      this.standardSrc = new MatTableDataSource(data);
      this.standardSrc.paginator = this.paginator;

    });
    this.standardSv.getListGeneralStandard(null).subscribe(data => {
      this.standardGeneral = new MatTableDataSource(data);
      this.standardGeneral.paginator = this.paginatorGeneral;

    })

  }

  ngAfterViewInit() {
    // this.standardSrc.paginator = this.paginator;
  }

  reset() {
    this.standdardSelected = new Standard();
  }

  addStandard() {
    this.standardCreate.idUser = this.authoSv.getUserId();
    this.standardSv.postStandard(this.standardCreate).subscribe(data => {
      this.standardSrc.data = this.standardSrc.data.concat(data);
      this.standardCreate = new Standard();

    });
  }

  editStandard(standard: Standard) {
    this.standdardSelected = Object.assign({}, standard);
  }
  submitEdit() {
    this.standardSv.postStandard(this.standdardSelected).subscribe(data => {
      this.standardSv.getCurrentSemStandard().subscribe(dt => {
        this.standardSrc = new MatTableDataSource(dt);
        this.standardSrc.paginator = this.paginator;
      });
      this.reset();
    });
  }

  removeStandard(standardId: Number) {
    this.standardSv.deleteStandard(standardId).subscribe(data => {
      this.standardSrc.data = this.standardSrc.data.filter(sta => sta.idStandard !== data);
    });
  }

  changeSemester(semNo) {
    this.standardSv.getListGeneralStandard(semNo).subscribe(data => {
      this.standardGeneral = new MatTableDataSource(data);
      this.standardGeneral.paginator = this.paginatorGeneral;

    });

  }

  applyFilter(filterValue: string, general = false) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    if (general) {
      this.standardGeneral.filter = filterValue;
    } else {
      this.standardSrc.filter = filterValue;
    }
  }



}
