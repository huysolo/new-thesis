import { Component, OnInit } from '@angular/core';
import { Meeting } from '../../meeting';
import { TimeLocation } from '../../time-location';
import { TaskService } from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import { MeetingService } from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { SemesterService } from '../../../core/semester.service';
import { LayoutService } from '../../../layout/layout.service';

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
  topicID: number;
  semesterState: number;

  constructor(public semService: SemesterService, private layoutSv: LayoutService,
    private route: ActivatedRoute,
    private meetingService: MeetingService,
    public authService: AuthService, private taskService: TaskService) { }

  ngOnInit() {
    this.layoutSv.labelName = 'Meeting';
    this.semService.init().subscribe(
      res => {
        if (res) {
          this.semesterState = this.semService.getState(this.semService.getCurrrentSem().semesterNo);
          this.route.params.subscribe(params => {
            this.type = params['typ'];
            if (this.type === 'recent') {


              if (this.authService.isStudent()) {
                this.stdGetRecentMeeting();
              }
              if (this.authService.isProfessor()) {
                this.profGetRecentMeeting();
                this.getTopicFromSemID(-1);
              }
            } else {
              if (this.authService.isStudent()) {
                this.stdGetHistoryMeeting();
              } else {
                this.profGetHistoryMeeting();
              }
            }
          });
          // if (this.semesterState != 0) {
          // }
        }
      }
    );

  }

  getListMeetingFromTopicID() {
    if (this.type == 'recent') {
      return this.meetingService.getListRecentMeetingFromTopicID(this.topicID).subscribe(
        res => {
          this.listMeeting = res;
        }
      );
    } else {
      return this.meetingService.getListHistoryMeetingFromTopicID(this.topicID).subscribe(
        res => {
          this.listMeeting = res;
        }
      );
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

  profGetRecentMeeting() {
    this.meetingService.profGetRecenMeeting().subscribe(
      res => {
        this.listMeeting = res;
      }
    );
  }
  profGetHistoryMeeting() {
    this.meetingService.profGetHistoryMeeting().subscribe(
      res => {
        this.listMeeting = res;
      }
    );
  }

  stdGetRecentMeeting() {
    this.meetingService.stdGetListRecentMeeting().subscribe(
      res => {
        this.listMeeting = res;
      }
    );
  }

  stdGetHistoryMeeting() {
    this.meetingService.stdGetListHistoryMeeting().subscribe(
      res => {
        this.listMeeting = res;
      }
    );
  }

  addNewMeeting(event: Meeting) {
    this.listMeeting.push(event);
  }

}
