import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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

  @Output('addNewMeeting') newMeeting = new EventEmitter<Meeting>();

  listAllStd: Array<any>;
  meetingCreate = new Meeting();
  listTopic: Array<any>;

  constructor(private taskService: TaskService, private meetingService: MeetingService,
     public authService: AuthService) { 
    this.InitNewMeeting();
  }

  ngOnInit() {
    if(this.authService.isStudent()){

    } else {
      this.getTopicFromSemID(-1);
    }
    
  }

  InitNewMeeting(){
    const temp = new TimeLocation();
    var date = new Date(); 
    var JSONdate = date.toJSON();
    temp.meetingTime = JSONdate.slice(0,16);
    this.meetingCreate.timeLocation.push(temp);
  }


  removeMeeting(i: number) {
    this.meetingCreate.timeLocation.splice(i, 1);
  }

  addTimeLocation(){
    const temp = new TimeLocation();
    this.meetingCreate.timeLocation.push(temp);
  }

  createMeeting(){
    this.meetingService.createMeeting(this.meetingCreate).subscribe(
      res => {
        this.newMeeting.emit(res);
      }
    );
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

