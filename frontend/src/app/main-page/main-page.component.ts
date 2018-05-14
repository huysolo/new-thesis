import { Component, OnInit } from '@angular/core';
import { AuthService } from '../core/auth.service';
import { TaskService } from '../task/task.service';
import { Observable } from 'rxjs/Observable';
import { Task } from '../models/Task';
import { Topic } from '../models/Topic';
import { TopicService } from '../topic/topic.service';
import { StudentDoTask } from '../task/components/student-do-task';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  displayedColumns = ['id', 'title', 'publishDate', 'studentCount', 'action'];
  displayedColumnsUser = ['studentId', 'stdName', 'teamlead'];
  listRecentTask: Observable<Task[]>;
  listRecentTopic: Observable<Topic[]>;
  listStudentTopic: Observable<StudentDoTask[]>;
  topic: Topic;
  constructor(public authoSv: AuthService, public taskSv: TaskService, public topicSv: TopicService) {
    this.listRecentTask = taskSv.getListTaskByApprove(0);
    this.listRecentTopic = topicSv.getListRecentTopic();
  }

  ngOnInit() {
  }

  getStudentTopic(topic: Topic) {
    this.topic = topic;
    this.listStudentTopic = this.topicSv.getAllStudentDoTopic(topic.idTop);
  }

}
