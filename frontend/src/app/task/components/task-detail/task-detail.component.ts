import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import {AuthService} from '../../../core/auth.service';
import {TaskService} from '../../task.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UploadFileService } from '../../upload-file.service';
import { Task } from '../../../models/Task';
import { MatDialog } from '@angular/material';
import {TaskEditComponent} from '../task-edit/task-edit.component';
import { SemesterService } from '../../../core/semester.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  issubmit: String;
  @Input() isTaskOwner = false;
  @Input() task: Task;
  @Input() isTeamlead = false;

  constructor(public dialog: MatDialog,
    public authService: AuthService, public taskService: TaskService, public uploadSv: UploadFileService,
    public semSv: SemesterService
  ) {
  }

  ngOnInit() {

  }

  showTask(task) {
    if (task.showFullTask === undefined) {
    task.showFullTask = true;
      this.loadStudent();
      console.log(this.task);
    } else {
      task.showFullTask = undefined;
    }
  }

  isSubmitTask() {
    if (this.issubmit == undefined) {
      this.issubmit = 'popup';
    } else {
      this.issubmit = undefined;
    }
  }

  submitToProf() {
    this.taskService.submitTask(this.task.idTask, 1).subscribe(
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
    } else {
      this.task.showCmt = undefined;
    }

  }

  sendComment(comment: String) {
    this.taskService.sendComment(comment, this.task.idTask).subscribe(
      res => {
      }
    );
  }

  reviewTask(pass: number) {
    this.taskService.reviewTask(this.task.idTask, pass).subscribe(
      res => {
        if (res != null) {
          this.task.pass = res.pass;
        }
      }
    );
  }
  loadStudent() {
    this.taskService.getListUserUpload(this.task.idTask).subscribe(data => {
      this.task.student = data;
      this.isTaskOwner = this.taskService.isBelongToTask(data);
    });
  }

  addversion(general: boolean) {
    if (general) {
      this.task.currentVersion = this.task.currentVersion + 1;
    } else {
      this.task.student.forEach(student => {
        if (student.userId == this.authService.getUserId()) {
          student.currentVersion = student.currentVersion + 1;
        }
      });
    }
  }

  openEditDialog(): void {
    const dialogRef = this.dialog.open(TaskEditComponent, {
      width: '500px',
      data: { task: this.task }
    });

    // dialogRef.afterClosed().subscribe(result => {
    //   if (result.idTask != null) {
    //     result.taskID = result.idTask;
    //     this.taskListSv.taskList = this.taskListSv.taskList.map(data => {
    //       return data;
    //     });
    //   }
    // });
  }

}
