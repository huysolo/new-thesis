import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../task.service';
import { ActivatedRoute } from '@angular/router';
import { Task } from '../../../models/Task';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-task-detail-page',
  templateUrl: './task-detail-page.component.html',
  styleUrls: ['./task-detail-page.component.css']
})
export class TaskDetailPageComponent implements OnInit {

  task: Observable<Task>;
  isTaskOwner = false;
  constructor(public taskSv: TaskService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.task = this.taskSv.getTaskById(param['id']).map(data => {
        data.showFullTask = true;
        this.taskSv.getListUserUpload(param['id']).subscribe(dt => {
          data.student = dt;
          this.isTaskOwner = this.taskSv.isBelongToTask(dt);
        });
        return data;
      });
    });
  }

}
