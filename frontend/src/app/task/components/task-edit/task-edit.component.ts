import { Component, OnInit, Input, Output, EventEmitter, Inject, NgZone } from '@angular/core';
import { StudentDoTask } from '../student-do-task';
import { TopicService } from '../../../topic/topic.service';
import { AuthService } from '../../../core/auth.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TaskDetailComponent } from '../task-detail/task-detail.component';
import { Task } from '../../../models/Task';
import { TaskInfo } from '../task-info';
import {TaskService} from '../../task.service';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {
  listAllStd: Array<StudentDoTask>;
  task: Task;
  constructor(public authService: AuthService, private topicService: TopicService,
    public dialogRef: MatDialogRef<TaskDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private taskService: TaskService) {
    this.task = data.task;
    this.task.deadline = new Date(this.task.deadline);
    console.log(this.task);
  }

  ngOnInit() {
    this.getListAllStd();
  }

  getListAllStd() {
    if (this.authService.isStudent()) {
      this.topicService.getAllStudentDoTopic(null).subscribe(data => {
        this.listAllStd = data;
        this.mapSelectStd();
      });
    }
  }

  mapSelectStd() {
    for (let i = 0; i < this.task.student.length; i++) {
      for (let j = 0; j < this.listAllStd.length; j++) {
        if (this.task.student[i].studentId == this.listAllStd[j].studentId) {
          this.listAllStd[j].selected = true;
        }
      }
    }
  }

  getValue(e, st: StudentDoTask) {
    st.selected = e;
  }

  editTask() {
    let temp = new TaskInfo();
    temp.taskID = this.task.idTask;
    temp.title = this.task.title;
    temp.description = this.task.description;
    temp.deadline = this.task.deadline;
    temp.studentIdList = [];
    for (let i = 0; i < this.listAllStd.length; i++) {
      if (this.listAllStd[i].selected) {
        temp.studentIdList.push(this.listAllStd[i].studentId);
      }
    }

    this.taskService.editTask(temp).subscribe(
      res => {
        console.log(res);
      }
    );

    console.log(temp);
  }

}
