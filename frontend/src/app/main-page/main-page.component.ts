import { Component, OnInit, AfterViewInit } from '@angular/core';
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
import { MatDialog } from '@angular/material';

import { MeetingCreateComponent } from '../meeting/component/meeting-create/meeting-create.component';
import { TaskCreateComponent } from '../task/components/task-create/task-create.component';
import { LayoutService } from '../layout/layout.service';
import { SemesterService } from '../core/semester.service';

import { MatStepper } from '@angular/material';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  displayedColumnsTopic = ['title', 'studentCount', 'action'];
  displayedColumnsUser = ['stdName', 'teamlead'];
  listRecentTask: Task[];

  listRecentTopic: Topic[];
  listStudentTopic: Observable<StudentDoTask[]>;
  recentTaskCount = 0;
  studentTopicCount = 0;
  recentTopicCount = 0;
  listRecentMeeting: Array<any>;
  countTopic: Observable<Number>;
  countTask: Number;
  countMeeting: number;
  countMessage: Observable<number>;
  stdTopicID: number;
  topic: Topic;
  listAllStd: Array<StudentDoTask>;
  semesterState: number;

  displayedColumnTask = ['title', 'deadline', 'pass'];
  displayedColumnMeeting = ['title', 'bookedSchedule', 'status'];

  constructor(public semService: SemesterService,
    private matdialog: MatDialog,
    public layoutSv: LayoutService,
    public authoSv: AuthService,
    public taskSv: TaskService,
    public topicSv: TopicService, public route: Router, private meetingService: MeetingService) {
    layoutSv.labelName = 'Dashboard';
    this.getListTask();
    this.getListMeeting();
    this.getTaskCount();
    this.getMeetingCount();
    if (this.authoSv.isStudent()) {
      this.countMessage = this.taskSv.countMessgeByStd();
      this.stdGetTopicID();
    }
  }

  ngAfterViewInit(): void {
  }

  ngOnInit() {
    this.semService.init().subscribe(
      res => {
        this.semesterState = this.semService.getState(this.semService.getCurrrentSem().semesterNo);
        this.getListTopic( this.semesterState);
      }
    );
    if (this.authoSv.isStudent()) {
      this.topicSv.getAllStudentDoTopic(null).subscribe(data => {
        this.listAllStd = data;
      });
    }
  }

  goForward(stepper: MatStepper) {
    stepper.next();
  }


  getStudentTopic(topic: Topic) {
    this.topic = topic;
    this.listStudentTopic = this.topicSv.getAllStudentDoTopic(topic.idTop).map(data => {
      this.studentTopicCount = data.length;
      return data;
    });
  }

  navigateToMeetingPage() {
    this.route.navigate(['/meeting/list/recent']);
  }

  navigateToTaskPage() {
    if (this.authoSv.isStudent()) {
      this.route.navigate(['/task/list', this.stdTopicID]);
    } else {
      this.route.navigate(['/task/list']);
    }
  }

  navigateToTopicPage() {
    this.route.navigate(['/topic/result']);
  }

  navigateToChatGroupPage() {
    this.route.navigate(['/task/chatgroup']);
  }

  navigateToTaskDetail(id) {
    this.route.navigate(['/task/task-detail', id]);
  }

  navigateToMeetingDetail(id) {
    this.route.navigate(['/meeting/meeting-detail', id]);
  }

  navigateToTopicDetail(id) {
    this.route.navigate(['/topic/detail', id]);
  }
  navigateToStudentDetail(id) {
    this.route.navigate(['/user/view', id]);
  }


  stdGetTopicID() {
    this.taskSv.stdGetTopicID().subscribe(
      res => {
        if (res) {
          this.stdTopicID = res;
        }
      });
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

  isMeetingToday(meetingTime: any) {
    const thisTime = new Date();
    const thisTimeInt = thisTime.getTime();
    const thisTimeDay = Math.floor(thisTimeInt / (1000 * 60 * 60 * 24));
    const meetingDay = Math.floor(meetingTime / (1000 * 60 * 60 * 24));
    if (thisTimeDay == meetingDay) {
      return true;
    } else {
      return false;
    }
  }

  addNewMeeting(event: Meeting) {
    this.listRecentMeeting.push(event);
    console.log(this.listRecentMeeting);
  }

  openDialog(): void {
    const dialogRef = this.matdialog.open(TaskCreateComponent, {
      width: '550px',
      data: { students: this.listAllStd }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        if (result.idTask != null) {
          //this.listRecentTask.push(result);
        }
      }
      
    });
  }


  getListTopic(semState) {
    if (this.authoSv.isStudent()) {
      this.topicSv.stdGetCurrTopic().subscribe(
        res => {
          if (res) {
            this.listRecentTopic = [];
            this.listRecentTopic[0] = res;
          }

        }
      );
    } else {
      if( semState == 0 ){
        this.topicSv.getListRecentTopic().subscribe(
          res => {
            if (res) {
              this.listRecentTopic = res;
            }
          }
        );
      } else {
        this.topicSv.profGetCurrAppliedTopic().subscribe(
          res => {
            if(res){
              this.listRecentTopic = res;
            }
          }
        );
      }
      
    }
  }

  getListTask() {
    if (this.authoSv.isStudent()) {
      this.taskSv.getMyTasks().subscribe(
        res => {
          if (res) {
            this.listRecentTask = res;
          }
        });
    } else {
      this.taskSv.getListTaskByApprove(0).subscribe(
        res => {
          if(res){
            this.listRecentTask = res;
          }
        }
      );
    }
  }




  getListMeeting() {
    if (this.authoSv.isStudent()) {
      this.meetingService.stdGetListRecentMeeting().subscribe(
        res => {
          this.listRecentMeeting = this.getBookedSchedule(res);
          if (this.listRecentMeeting) {
          } else {
            console.log('null');
          }

        }
      );
    } else {
      this.meetingService.profGetRecenMeeting().subscribe(
        res => {
          this.listRecentMeeting = this.getBookedSchedule(res);
          if (this.listRecentMeeting) {
          } else {
            console.log('null');
          }
        }
      );
    }
  }

  getTaskCount() {
    if (this.authoSv.isStudent()) {
      this.taskSv.countTaskByStd().subscribe(
        res => {
          if (res) {
            this.countTask = res;
          }
        }
      );
    } else {
      this.taskSv.countTask().subscribe(
        res => {
          if (res) {
            this.countTask = res;
          }
        }
      );
    }
  }

  getMeetingCount() {
    if (this.authoSv.isStudent()) {
      this.meetingService.countMeetingByStd().subscribe(
        res => {
          if (res) {
            this.countMeeting = res;
          }
        }
      );
    } else {
      this.meetingService.countMeetingByProf().subscribe(
        res => {
          if (res) {
            this.countMeeting = res;
          }
        }
      );
    }
  }
}


