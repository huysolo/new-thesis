import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-task-pagination',
  templateUrl: './task-pagination.component.html',
  styleUrls: ['./task-pagination.component.css']
})
export class TaskPaginationComponent implements OnInit {

  @Input('pageCount') pageCount: Array<number>;
  @Input('page') page: number;
  @Output('setPage') newPage = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  sendPage(i) {
    if (i >= 0 && i < this.pageCount.length) {
      this.newPage.emit(i);
    }
  }

  isActive(i) {
    if (i == this.page) {
      return 'active';
    } else {
      return null;
    }
  }

}
