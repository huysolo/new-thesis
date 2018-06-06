import { Component, OnInit, Input, ViewChild, Output, EventEmitter, AfterViewChecked, AfterViewInit } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
import { TopicService } from '../../topic.service';
import { SemesterService } from '../../../core/semester.service';
import { Disapprove } from '../../../models/Disapprove';

@Component({
  selector: 'app-topic-table',
  templateUrl: './topic-table.component.html',
  styleUrls: ['./topic-table.component.css']
})
export class TopicTableComponent implements OnInit {


  @Input() dataSource: MatTableDataSource<Topic>;

  @Input() actionButtonClass = '';
  @Input() showDelete = false;
  @Input() showEdit = false;
  @Input() showPublish = false;
  @Input() displayFull = true;
  

  @Output() emitTopic = new EventEmitter<Topic>();
  @Output() publish = new EventEmitter<Topic>();

  displayedColumns = ['idTop', 'stNumLimit', 'title', 'action'];
  displayedColumnsFull = ['idTop', 'stNumLimit', 'sem', 'publishDate', 'title', 'action'];

  constructor(public topicSv: TopicService, public semSv: SemesterService) { }

  ngOnInit() {

  }

  delete(idTop) {
    this.topicSv.deleteTopic(idTop).subscribe(data => {
      this.dataSource.data = this.dataSource.data.filter(topic => topic.idTop != idTop);
    });
  }

  edit(topic: Topic) {
    if (topic !== undefined) {
      this.dataSource.data = this.dataSource.data.map(data => data.idTop === topic.idTop ? topic : data);
    }
  }

  publishTopic(idTop) {
    this.topicSv.publishTopic(idTop).subscribe(data => {
      this.dataSource.data = this.dataSource.data.filter(topic => topic.idTop != idTop);
      this.publish.emit(data);
    });
  }

  approve(idTopic) {
    const disapprove = new Disapprove(idTopic, '');
    this.topicSv.disapproveTopic(disapprove).subscribe(data => {
      const topLst = this.dataSource.data;
      topLst.forEach((element, index) => {
        if (element.idTop == data.idTop) {
          topLst[index] = data;
        }
      });
      this.dataSource.data = topLst;
    });
  }

  disapprove(topic) {
    const topLst = this.dataSource.data;
    topLst.forEach((element, index) => {
      if (element.idTop == topic.idTop) {
        topLst[index] = topic;
      }
    });
    this.dataSource.data = topLst;
  }




}
