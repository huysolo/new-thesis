import { Injectable } from '@angular/core';
import { Task } from '../models/Task';
import { Observable } from 'rxjs/Observable';
import { TaskService } from './task.service';
import { Topic } from '../models/Topic';

@Injectable()
export class TaskListService {
  constructor(private taskSv: TaskService) {
    this.topic = new Topic();
    this.topic.semesterNo = 0;
    this.topic.idTop = 0;
  }

  topic: Topic;

  titleFilter = '';

  selectedPage: Number;
  pageCount: Number[];
  taskList: Observable<Task[]>;

  getPage(page: Number) {
    if (page == null) {
      this.selectedPage = 0;
    } else {
      this.selectedPage = page;
    }
    if (this.topic != null) {
      this.taskList = this.getTaskList(this.topic.idTop);
    }
  }

  getTaskList(topicId) {
    return this.taskSv.getPage(topicId, this.selectedPage, this.titleFilter).map(
      res => {
        if(res){
          this.pageCount = new Array(res.pageCount);
          return res.taskList;
        }
        
      }
    );
  }


}
