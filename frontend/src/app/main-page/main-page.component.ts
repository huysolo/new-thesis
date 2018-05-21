import { Component, OnInit } from '@angular/core';
import { AuthService } from '../core/auth.service';
import { TaskService } from '../task/task.service';
import { Observable } from 'rxjs/Observable';
import { Task } from '../models/Task';
import { Topic } from '../models/Topic';
import { TopicService } from '../topic/topic.service';
import { StudentDoTask } from '../task/components/student-do-task';
import { Router } from '@angular/router';
import { Meeting } from '../meeting/meeting';
import { MeetingService } from '../meeting/meeting.service';
import { MatTableDataSource } from '@angular/material';
import {MeetingCreateComponent} from '../meeting/component/meeting-create/meeting-create.component';
import { LayoutService } from '../layout/layout.service';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  displayedColumnsTopic = ['title', 'studentCount', 'action'];
  displayedColumnsUser = ['stdName', 'teamlead'];

  listRecentTask: Observable<Task[]>;
  listRecentTopic: Observable<Topic[]>;
  listStudentTopic: Observable<StudentDoTask[]>;
  listRecentMeeting: Array<any>;
  countTopic: Observable<Number>;
  countTask: Observable<Number>;
  topic: Topic;


  displayedColumnTask = ['title', 'deadline', 'pass'];
  displayedColumnMeeting = ['title', 'bookedSchedule', 'status'];


  constructor(
    public authoSv: AuthService, public taskSv: TaskService, public topicSv: TopicService,
    public route: Router, private meetingService: MeetingService, layoutSv: LayoutService) {
    this.listRecentTask = taskSv.getListTaskByApprove(0);
    this.listRecentTopic = topicSv.getListRecentTopic();
    this.countTopic = topicSv.countTopic();
    this.countTask = taskSv.countTask();
    layoutSv.labelName = 'Dashboard';
  }

  ngOnInit() {
    this.profGetRecentMeeting();
  }


  getStudentTopic(topic: Topic) {
    this.topic = topic;
    this.listStudentTopic = this.topicSv.getAllStudentDoTopic(topic.idTop);
  }


  navigateToTaskDetail(id) {
    this.route.navigate(['/task/task-detail', id]);
  }

  navigateToMeetingDetail(id) {
    this.route.navigate(['/meeting/meeting-detail', id]);
  }

  profGetRecentMeeting() {
    this.meetingService.profGetRecenMeeting().subscribe(
      res => {
        this.listRecentMeeting = this.getBookedSchedule(res);
        console.log(this.listRecentMeeting);
        if (this.listRecentMeeting) {
        } else {
          console.log('null');
        }

      }
    );
  }

  getBookedSchedule(listMeeting: Array<any>) {
    if (listMeeting.length > 0) {
      for (let i = 0; i < listMeeting.length; i++) {
        for (let j = 0; j < listMeeting[i].timeLocation.length; j++) {
          if (listMeeting[i].timeLocation[j].status) {
            listMeeting[i].bookedSchedule = listMeeting[i].timeLocation[j];
          }
        }
      }
    }
    return listMeeting;
  }

  isMeetingToday(meetingTime: any){
    var thisTime = new Date();
    var thisTimeInt = thisTime.getTime();
    var thisTimeDay = Math.floor(thisTimeInt/(1000 * 60 * 60 * 24)) + 1; 
    var meetingDay = Math.floor(meetingTime/(1000 * 60 * 60 * 24));
    if(thisTimeDay == meetingDay){
      return true;
    } else {
      return false;
    }
  }

  addNewMeeting(event: Meeting){
    this.listRecentMeeting.push(event);
  }


}


