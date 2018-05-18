import { Component, OnInit } from '@angular/core';
import { TaskInfo } from '../task-info';
import { TaskService } from '../../task.service';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { StudentDoTask } from '../student-do-task';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { TaskDetailComponent } from '../task-detail/task-detail.component';
import { MatDialog } from '@angular/material';
import { TaskCreateComponent } from '../task-create/task-create.component';
import { TopicService } from '../../../topic/topic.service';
import { Task } from '../../../models/Task';
import { Topic } from '../../../models/Topic';
import { SemesterService } from '../../../core/semester.service';
import { Observable } from 'rxjs/Observable';
import { TaskListService } from '../../task-list.service';


@Component({
  selector: 'app-task-content',
  templateUrl: './task-content.component.html',
  styleUrls: ['./task-content.component.css']
})
export class TaskContentComponent implements OnInit {
  listTask: Array<Task>;

  searchText: String = '';
  public page: number;
  pagecount: Array<number>;

  disconnection: any;

  type: String;
  isrecent: boolean;
  ishistory: boolean;
  listTopic: Array<any>;
  listSem: Array<any>;

  topicID: any;
  isCreateTask: Boolean;
  listAllStd: Array<StudentDoTask>;
  topic: Topic;




  constructor(
    public taskService: TaskService, public authService: AuthService, private route: ActivatedRoute,
    public dialog: MatDialog, private topicSv: TopicService, public semSv: SemesterService,
    public taskListSv: TaskListService
  ) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.topicSv.getTopicById(params['id']).subscribe(data => {
        this.taskListSv.topic = data;
        this.taskListSv.getPage(0);
      });
      this.listTask = null;
      this.page = 0;
      if (this.authService.isStudent()) {
        this.topicSv.getAllStudentDoTopic(null).subscribe(data => {
          this.listAllStd = data;
        });
      }

    });
  }

  getPage(topicID: number, page: Number, title) {
    if (page == null) {
      page = 0;
      this.page = 0;
    }
    this.taskService.getPage(topicID, page, title).subscribe(
      res => {
        this.pagecount = new Array(res.pageCount);
        this.listTask = res.taskList;
      }
    );
  }

  setPage(event: number) {
    if (event >= 0 && event < this.pagecount.length) {
      this.page = event;
      this.getPage(this.topicID, event, this.searchText);
    }
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

  addNewTask(event: any) {
    this.listTask.push(event);
    this.isCreateTask = false;
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(TaskCreateComponent, {
      width: '400px',
      data: { students: this.listAllStd }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.idTask != null) {
        result.taskID = result.idTask;
        this.listTask.unshift(result);
      }
    });
  }


}
