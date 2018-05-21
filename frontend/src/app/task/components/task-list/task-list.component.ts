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
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  listTask: Array<Task>;

  listTopic: Array<any>;
  listSem: Array<any>;

  constructor(public taskService: TaskService, public authService: AuthService, private route: ActivatedRoute,
    public dialog: MatDialog, private topicSv: TopicService,
    public taskListSv: TaskListService, private layoutSv: LayoutService
  ) {
    layoutSv.labelName = 'Task';
  }

  ngOnInit() {
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


  stdGetListTopic() {
    this.taskService.getTopicFromStd().subscribe(
      res => {
        this.listTopic = res;
      }
    );
  }


}
