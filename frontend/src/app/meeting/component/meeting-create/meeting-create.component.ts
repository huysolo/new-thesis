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

    } else {
      this.getTopicFromSemID(-1);
    }
    
  }

  newMeeting(){
    const temp = new TimeLocation();
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
    console.log(this.meetingCreate);
    this.meetingService.createMeeting(this.meetingCreate).subscribe(
      res => {
        console.log(res);
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

//   let d1 = new Date('2018-05-20 03:31:00.0')
// undefined
// d1.getTime
// ƒ getTime() { [native code] }
// d1.getTime()
// 1526761860000

}

