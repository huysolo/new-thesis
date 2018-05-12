import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import {AuthService} from '../../../core/auth.service';
import {TaskService} from '../../task.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UploadFileService } from '../../upload-file.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  listTask: Array<any>;
  issubmit: String;
  @Input() task: any;

  constructor(public authService: AuthService, public taskService: TaskService, public uploadSv: UploadFileService) { 
  }

  ngOnInit() {
  }

  showTask(task) {
    if (task.showFullTask === undefined) {
    task.showFullTask = true;
      this.loadStudent();
    } else {
      task.showFullTask = undefined;
    }
  }

  isSubmitTask(){
    if(this.issubmit == undefined){
      this.issubmit = 'popup';
    } else {
      this.issubmit = undefined;
    }
  }

  submitToProf() {
    this.taskService.submitTask(this.task.taskID, 1).subscribe(
      res => {
        if (res != null) {
          this.task.submit = 1;
        }
      }
    );
    this.issubmit = undefined;
  }



  getTaskComment() {
    if (this.task.showCmt === undefined) {
      this.task.showCmt = true;
      // this.taskService.getTaskComment(this.task.taskID).subscribe(
      //   res => {
      //     this.task.comment = res;
      //   }
      // );

      // const stompClient = this.taskService.receiveMessage();
      // stompClient.connect({}, frame => {
      //   this.task.disconnection = stompClient.subscribe<any>('/topic/comment' + this.task.taskID, res => {
      //     this.task.comment.push(JSON.parse(res.body));
      //   });
      // });
    } else {
      this.task.showCmt = undefined;
      // this.task.disconnection.unsubscribe();
    }

  }

  sendComment(comment: String) {
    this.taskService.sendComment(comment, this.task.taskID).subscribe(
      res => {
      }
    );
  }

  reviewTask(pass: number) {
    this.taskService.reviewTask(this.task.taskID, pass).subscribe(
      res => {
        if (res != null) {
          this.task.pass = res.pass;
        }
      }
    );
  }
  loadStudent() {
    console.log(this.task.taskID);
    
    this.taskService.getListUserUpload(this.task.taskID).subscribe(data => {
      this.task.student = data;
    });
  }

}
