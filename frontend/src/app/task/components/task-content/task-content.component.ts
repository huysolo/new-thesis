import { Component, OnInit } from '@angular/core';
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

  listAllStd: Array<StudentDoTask>;

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

  openDialog(): void {
    const dialogRef = this.dialog.open(TaskCreateComponent, {
      width: '400px',
      data: { students: this.listAllStd }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.idTask != null) {
        result.taskID = result.idTask;
        this.taskListSv.taskList = this.taskListSv.taskList.map(data => {
          return data;
        });
      }
    });
  }


}
