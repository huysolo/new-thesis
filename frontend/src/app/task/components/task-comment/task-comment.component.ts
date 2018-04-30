import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import {TaskService} from '../../task.service';

@Component({
  selector: 'app-task-comment',
  templateUrl: './task-comment.component.html',
  styleUrls: ['./task-comment.component.css']
})
export class TaskCommentComponent implements OnInit, OnDestroy {
  taskCmt: Array<any>;
  connection: any;
  @Input('taskid') taskid: number;
  constructor(private taskService: TaskService) { 
    
  }

  ngOnInit() {
    this.taskService.getTaskComment(this.taskid).subscribe(
      res => {
        this.taskCmt = res;
      }
    );

    const stompClient = this.taskService.receiveMessage();
    stompClient.connect({}, frame => {
      this.connection = stompClient.subscribe<any>('/topic/comment' + this.taskid, res => {
        this.taskCmt.push(JSON.parse(res.body));
      });
    });
  }

  ngOnDestroy() {
  this.connection.unsubscribe();
  }

  sendComment(comment: String) {
    this.taskService.sendComment(comment, this.taskid).subscribe(
      res => {
      }
    );
  }

}
