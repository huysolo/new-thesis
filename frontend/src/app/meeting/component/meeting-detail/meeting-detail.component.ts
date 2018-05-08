import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {Meeting} from '../../meeting';
import { TimeLocation } from '../../time-location';
import {TaskService} from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import {MeetingService} from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-meeting-detail',
  templateUrl: './meeting-detail.component.html',
  styleUrls: ['./meeting-detail.component.css']
})
export class MeetingDetailComponent implements OnInit {
  @Input('meeting') meeting: Meeting;
  listAllStd: Array<any>;
  constructor(private meetingService: MeetingService, public authService: AuthService) { }

  ngOnInit() {
    this.newTimeLocation();
    if(this.authService.isStudent()){
      this.getAllStudentDoTopic();
    }
  }

  newTimeLocation(){
    if(this.authService.isProfessor() && this.meeting.timeLocation.length == 0){
      this.meeting.timeLocation = [];
      const temp = new TimeLocation();
      this.meeting.timeLocation.push(temp);
    }
  }


  getAllStudentDoTopic() {
    this.meetingService.getAllStudentDoTopic().subscribe(
      res => {
        if(res != null){
          this.listAllStd = res;
        }
        else {
          console.log('ko co gia tri');
        }       
      });
  }

  isActiveStd(list): void {
    if (list.class === undefined) {
      list.class = 'active';
      const temp = <StudentMeeting>({ name: list.name, stdID: list.stdID });
      this.meeting.student.push(temp);
    } else {
      list.class = undefined;
      for (let i = 0; i < this.meeting.student.length; i++) {
        if (this.meeting.student[i].name === list.name) {
          this.meeting.student.splice(i, 1);
        }
      }
    }
  }

  stdBookLocationTime(event, schedule){
    if(this.authService.isTeamLead()){
      for(let i = 0; i < this.meeting.timeLocation.length; i++){
        if(this.meeting.timeLocation[i].location == schedule.location 
          && this.meeting.timeLocation[i].meetingTime == schedule.meetingTime){
            this.meeting.timeLocation[i].status = 1;
          } else {
            this.meeting.timeLocation[i].status = 0;
          }
      }
    }
  }

  removeMeeting(i: number) {
    this.meeting.timeLocation.splice(i, 1);
  }

  addTimeLocation(){
    const temp = new TimeLocation();
    this.meeting.timeLocation.push(temp);
  }

  editMeeting(){
    console.log(this.meeting);
    this.meetingService.editMeeting(this.meeting).subscribe(
      res => {
        console.log(res);
      }
    );
  }

}
