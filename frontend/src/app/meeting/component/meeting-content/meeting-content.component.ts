import { Component, OnInit } from '@angular/core';
import { Meeting } from '../../meeting';
import { TimeLocation } from '../../time-location';
import { TaskService } from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import { MeetingService } from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-meeting-content',
  templateUrl: './meeting-content.component.html',
  styleUrls: ['./meeting-content.component.css']
})
export class MeetingContentComponent implements OnInit {
  listMeeting: Array<any>;
  t: Date;
  listTopic: Array<any>;
  type: String;

  constructor(private route: ActivatedRoute, private meetingService: MeetingService, public authService: AuthService, private taskService: TaskService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.type = params['typ'];
      if (this.type === 'recent') {
        if(this.authService.isStudent()){
          this.stdGetRecentMeeting();
        }
        if(this.authService.isProfessor()){
          this.profGetRecentMeeting();
        }
      } else {
        if(this.authService.isStudent()){
          this.stdGetHistoryMeeting();
        } else {
          this.profGetHistoryMeeting();
        }
      }
    });

  }

  getListMeetingFromTopicID(topicid: number) {
    return this.meetingService.getListMeetingFromTopicID(topicid).subscribe(
      res => {
        this.listMeeting = res;
        console.log(this.listMeeting);
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

profGetRecentMeeting(){
  this.meetingService.profGetRecenMeeting().subscribe(
    res => {
      this.listMeeting = res;
    }
  );
}
profGetHistoryMeeting(){
  this.meetingService.profGetHistoryMeeting().subscribe(
    res => {
      this.listMeeting = res;
    }
  );
}

stdGetRecentMeeting(){
  this.meetingService.stdGetListRecentMeeting().subscribe(
    res => {
      this.listMeeting = res;
    }
  );
}

stdGetHistoryMeeting(){
  this.meetingService.stdGetListHistoryMeeting().subscribe(
    res => {
      this.listMeeting = res;
    }
  );
}

addNewMeeting(event: any){
  this.listMeeting.push(event);
}

}
