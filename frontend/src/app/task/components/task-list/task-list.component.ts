import { Component, OnInit } from '@angular/core';
import {TaskService} from '../../task.service';
import { Task } from '../../../models/Task';
import { StudentDoTask } from '../student-do-task';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { TopicService } from '../../../topic/topic.service';
import { TaskCreateComponent } from '../task-create/task-create.component';
import { TaskListService } from '../../task-list.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  listTask: Array<Task>;

  searchText: String = '';
  public page: number;

  disconnection: any;
  listTopic: Array<any>;
  listSem: Array<any>;

  topicId: any;
  isCreateTask: Boolean;
  listAllStd: Array<StudentDoTask>;

  constructor(public taskService: TaskService, public authService: AuthService, private route: ActivatedRoute,
    public dialog: MatDialog, private topicSv: TopicService,
    public taskListSv: TaskListService
  ) {

  }

  ngOnInit() {
    this.page = 0;
    this.listTask = null;
    if (this.authService.isProfessor()) {
      this.getSem();
    } else {
      this.stdGetListTopic();
    }
  }


  getTopicFromSemID(semNo) {
    this.taskService.getTopicFromSemID(semNo).subscribe(
      res => {
        this.listTopic = res;
      }
    );
  }

  getSem() {
    this.taskService.getSemCount().subscribe(
      res => {
        this.listSem = res;
      }
    );
  }

  isActive(i) {
    if (i == this.page) {
      return 'active';
    } else {
      return null;
    }
  }

  stdGetListTopic() {
    this.taskService.getTopicFromStd().subscribe(
      res => {
        this.listTopic = res;
      }
    );
  }

  createTask() {
    this.isCreateTask = true;
  }

  switchIsCreate(event: Boolean) {
    this.isCreateTask = event;
  }

  addNewTask(event: any){
    this.listTask.push(event);
    this.isCreateTask = false;
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(TaskCreateComponent, {
      width: '400px',
      data: {students: this.listAllStd}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.idTask != null) {
        result.taskID = result.idTask;
        this.listTask.unshift(result);
      }
    });
  }

}
