import { Component, OnInit } from '@angular/core';
import {Meeting} from '../../meeting';
import { TimeLocation } from '../../time-location';
import {TaskService} from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import {MeetingService} from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-meeting-create',
  templateUrl: './meeting-create.component.html',
  styleUrls: ['./meeting-create.component.css']
})
export class MeetingCreateComponent implements OnInit {

  listAllStd: Array<any>;
  meetingCreate = new Meeting();
  listTopic: Array<any>;

  constructor(private taskService: TaskService, private meetingService: MeetingService,
     public authService: AuthService) { 
    this.newMeeting();
  }

  ngOnInit() {
    if(this.authService.isStudent()){
      this.getAllStudentDoTopic();
    } else {
      this.getTopicFromSemID(-1);
    }
    
  }

  newMeeting(){
    const temp = new TimeLocation();
    this.meetingCreate.timelocation.push(temp);
  }

  removeMeeting(i: number) {
    this.meetingCreate.timelocation.splice(i, 1);
  }

  addTimeLocation(){
    const temp = new TimeLocation();
    this.meetingCreate.timelocation.push(temp);
  }

  createMeeting(){
    console.log(this.meetingCreate);
  }

  getAllStudentDoTopic() {
    this.meetingService.getAllStudentDoTopic().subscribe(
      res => {
        if(res != null){
          this.listAllStd = res;
          console.log(res);
        }
        else {
          console.log('ko co gia tri');
        }
        
      });
  }

  isActiveStd(list): void {
    if (list.class === undefined) {
      list.class = 'active';
      const temp = <StudentMeeting>({ name: list.name, stdid: list.stdID });
      this.meetingCreate.student.push(temp);
    } else {
      list.class = undefined;
      for (let i = 0; i < this.meetingCreate.student.length; i++) {
        if (this.meetingCreate.student[i].name === list.name) {
          this.meetingCreate.student.splice(i, 1);
        }
      }
    }
  }

  getTopicFromSemID(semid) {
    this.taskService.getTopicFromSemID(semid).subscribe(
      res => {
        if (res == null) {
          console.log('We dont have Semester now');
        } else {
          this.listTopic = res;
        }
      }
    );
  }

}

