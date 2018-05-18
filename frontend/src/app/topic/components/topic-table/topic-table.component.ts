import { Component, OnInit, Input, ViewChild, Output, EventEmitter, AfterViewChecked, AfterViewInit } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Topic } from '../../../models/Topic';
import { SemesterService } from '../../../core/semester.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-topic-table',
  templateUrl: './topic-table.component.html',
  styleUrls: ['./topic-table.component.css']
})
export class TopicTableComponent implements OnInit {


  // @Input() tableTilte = '';
  @Input() dataSource: MatTableDataSource<Topic>;
  // @Input() icon = '';
  @Input() actionButtonClass = '';
  // @Input() semmesterFilter = false;
  @Input() displayFull = true;
  @Output() emitTopic = new EventEmitter<Topic>();
  @Output() emitSemNo = new EventEmitter<number>();
  displayedColumns = ['idTop', 'title', 'stNumLimit', 'action'];
  displayedColumnsFull = ['idTop', 'title', 'stNumLimit', 'publishDate', 'sem', 'action'];

  constructor(public semSv: SemesterService) { }

  ngOnInit() {

  }


}
