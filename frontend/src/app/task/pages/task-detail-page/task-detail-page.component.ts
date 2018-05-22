import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../task.service';
import { ActivatedRoute } from '@angular/router';
import { Task } from '../../../models/Task';
import { Observable } from 'rxjs/Observable';
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-task-detail-page',
  templateUrl: './task-detail-page.component.html',
  styleUrls: ['./task-detail-page.component.css']
})
export class TaskDetailPageComponent implements OnInit {

  task: Task;
  isTaskOwner = false;
  constructor(public taskSv: TaskService, public route: ActivatedRoute, public layoutSv: LayoutService) {
  }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.taskSv.getTaskById(param['id']).map(data => {
        this.layoutSv.labelName = 'Task detail: ' + param['id'];
        data.showFullTask = true;
        this.taskSv.getListUserUpload(param['id']).subscribe(dt => {
          data.student = dt;
          this.isTaskOwner = this.taskSv.isBelongToTask(dt);
        });
        return data;
      }).subscribe( dt => {
        this.task = dt;
      });
    });
  }

}
