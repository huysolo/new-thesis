import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Meeting } from '../../meeting';
import { TimeLocation } from '../../time-location';
import { TaskService } from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import { MeetingService } from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-meeting-detail',
  templateUrl: './meeting-detail.component.html',
  styleUrls: ['./meeting-detail.component.css']
})
export class MeetingDetailComponent implements OnInit {
  @Input('meeting') meeting: Meeting;
  listAllStd: Array<any>;
  aaa: Boolean = true;

  constructor(private meetingService: MeetingService, public authService: AuthService) { }

  ngOnInit() {
    this.newTimeLocation();
    if (this.authService.isStudent()) {
      this.getAllStudentDoTopic();     
    }

    this.toDateTimeLocal();

  }

  newTimeLocation() {
    if (this.authService.isProfessor() && this.meeting.timeLocation.length == 0) {
      this.meeting.timeLocation = [];
      const temp = new TimeLocation();
      this.meeting.timeLocation.push(temp);
    }
  }


  getAllStudentDoTopic() {
    this.meetingService.getAllStudentDoTopic().subscribe(
      res => {
        if (res != null) {
          this.listAllStd = res;
          this.listActiveStd();
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

  listActiveStd(){
    if(this.listAllStd != undefined){
      for(let i = 0; i< this.meeting.student.length; i++){
        for(let j = 0; j< this.listAllStd.length; j++){
          if(this.meeting.student[i].name == this.listAllStd[j].name){
            this.listAllStd[j].class = 'active';
          }
        }
      }
    }
  }

  stdBookLocationTime(event, schedule) {
    if (this.authService.isTeamLead()) {
      for (let i = 0; i < this.meeting.timeLocation.length; i++) {
        if (this.meeting.timeLocation[i].location == schedule.location
          && this.meeting.timeLocation[i].meetingTime == schedule.meetingTime) {
          this.meeting.timeLocation[i].status = 1;
        } else {
          this.meeting.timeLocation[i].status = 0;
        }
      }
    }
    console.log(this.meeting.timeLocation);
  }

  removeMeeting(i: number) {
    this.meeting.timeLocation.splice(i, 1);
  }

  addTimeLocation() {
    const temp = new TimeLocation();
    this.meeting.timeLocation.push(temp);
  }

  editMeeting() {
    console.log(this.meeting);
    this.meetingService.editMeeting(this.meeting).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  toDateTimeLocal() {
    if(this.meeting.timeLocation.length > 0){
      for (let i = 0; i < this.meeting.timeLocation.length; i++) {
        var ts = this.meeting.timeLocation[i].meetingTime;
        var t1 = Number(ts);
        var date = new Date(t1); 
        var JSONdate = date.toJSON();
        this.meeting.timeLocation[i].meetingTime = JSONdate.slice(0,16);
      }
    }
    
  }

  isCheckedSchedule(schedule:TimeLocation){
    if(schedule.status == 1){
      return '';
    } else {
      return null;
    }
  }

  cancelMeeting(){
    this.meetingService.cancelMeeting(this.meeting).subscribe(
      res => {
        if(res != null){
          this.meeting.status = 2;
          console.log(this.meeting);
        }
      }
    );
  }

}
