import { Component, OnInit } from '@angular/core';
import { Meeting } from '../../meeting';
import { TimeLocation } from '../../time-location';
import { TaskService } from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import { MeetingService } from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-meeting-content',
  templateUrl: './meeting-content.component.html',
  styleUrls: ['./meeting-content.component.css']
})
export class MeetingContentComponent implements OnInit {
  listMeeting: Array<any>;
  t: Date;
  listTopic: Array<any>;

  constructor(private meetingService: MeetingService, public authService: AuthService, private taskService: TaskService) { }

  ngOnInit() {
    if(this.authService.isStudent()){
      this.getListMeetingFromTopicID(-1);
    } else {
      this.getTopicFromSemID(-1);
    }

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

}
