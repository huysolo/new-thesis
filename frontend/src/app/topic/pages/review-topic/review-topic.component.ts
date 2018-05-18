import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Topic } from '../../../models/Topic';
import { TopicService } from '../../topic.service';
import { CommonService } from '../../../core/common.service';
import { StandardService } from '../../../standard.service';
import { ReviewTopic } from '../../../models/ReviewTopic';
import { TopicSemStandard } from '../../../models/TopicSemStandard';
import { Standard } from '../../../models/Standard';
import { StandardScore } from '../../../models/StandardScore';
import { MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import { DataSource } from '@angular/cdk/table';


@Component({
  selector: 'app-review-topic',
  templateUrl: './review-topic.component.html',
  styleUrls: ['./review-topic.component.css']
})
export class ReviewTopicComponent implements OnInit, AfterViewInit {

  listSem: Observable<Number[]>;
  reviewTp: ReviewTopic;
  topicSelected: Topic;
  topicRvStandardDetail: TopicSemStandard[];
  standardListReview: Standard[];
  dataSource: MatTableDataSource<Topic>;
  dataSourceRe: MatTableDataSource<Topic>;
  displayedColumns = ['idTop', 'title', 'stNumLimit', 'action'];
  displayedColumnsFull = ['idTop', 'title', 'stNumLimit', 'publishDate', 'action'];
  selectedSem;
  typ: String;
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('sort') sort: MatSort;
  @ViewChild('paginatorRe') paginatorRe: MatPaginator;
  @ViewChild('sortRe') sortRe: MatSort;
  constructor(private route: ActivatedRoute,
    public topicSv: TopicService, public comSv: CommonService, public standardSv: StandardService) { }


  ngAfterViewInit() {

  }
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.topicRvStandardDetail = null;
      this.typ = params['typ'];
      this.topicSv.getListTopicReview(null, 0, this.typ === 'guide').subscribe(lst => {
        this.dataSourceRe = new MatTableDataSource(lst);
        this.dataSourceRe.paginator = this.paginatorRe;
      });
      this.topicSv.getListTopicReview(null, 1, this.typ === 'guide').subscribe(lst => {
        this.dataSource = new MatTableDataSource(lst);
        this.dataSource.paginator = this.paginator;
      });
      this.listSem = this.comSv.getAllSemNo();
    });
  }



  onChangeSemester(sem) {
    this.selectedSem = sem;
    this.topicSv.getListTopicReview(sem, 1, this.typ === 'guide').subscribe(lst => {
      this.dataSource = new MatTableDataSource(lst);
      this.dataSource.paginator = this.paginator;
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

  setSlTopic(tp: Topic) {
    this.topicSelected = tp;
  }

  submitReview() {
    this.standardSv.postReview(this.reviewTp).subscribe(data => {
      this.topicSv.getListTopicReview(null, 0, this.typ === 'guide').subscribe(lst => {
        this.dataSourceRe = new MatTableDataSource(lst);
        this.dataSourceRe.paginator = this.paginatorRe;
        this.standardListReview = null;

        this.onChangeSemester(this.selectedSem);
      });
    });
  }

  finalScore() {
    let rs = 0;
    let co = 0;
    if (this.standardListReview != null && this.reviewTp.standardScores != null) {
      this.standardListReview.forEach((dt, i) => {
        co = co + dt.coefficient;
        if (this.reviewTp.standardScores[i] != null && this.reviewTp.standardScores[i].score != null) {
          rs = rs + (this.reviewTp.standardScores[i].score as number) * dt.coefficient ;
        }
      });
    }
    return (rs / (co === 0 ? 0 : co)).toFixed(2);
  }

  finalScored() {
    let rs = 0;
    let co = 0;
    if (this.topicRvStandardDetail != null) {
        this.topicRvStandardDetail.forEach((dt, i) => {
            co = co + dt.coefficient ;
            rs = rs + dt.score * dt.coefficient ;
        });

    }
    return (rs / (co === 0 ? 0 : co)).toFixed(2);
  }

  detail(topicId: Number) {
    this.standardSv.getReview(topicId).subscribe(data => {
      this.topicRvStandardDetail = data;
    });
  }

  applyFilter(filterValue: string, isReviewed: boolean) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    if (isReviewed) {
      this.dataSource.filter = filterValue;
    } else {
      this.dataSourceRe.filter = filterValue;
    }
  }
  getDate(num: number) {
    const d = new Date(num);
    return d;
  }

}
