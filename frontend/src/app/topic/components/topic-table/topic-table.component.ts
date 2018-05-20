import { Component, OnInit, Input, ViewChild, Output, EventEmitter, AfterViewChecked, AfterViewInit } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
import { TopicService } from '../../topic.service';

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
  displayedColumnsFull = ['idTop', 'stNumLimit', 'publishDate', 'sem', 'title', 'action'];

  constructor(public topicSv: TopicService) { }

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




}
