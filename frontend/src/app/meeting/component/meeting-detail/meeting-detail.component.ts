import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Meeting } from '../../meeting';
import { TimeLocation } from '../../time-location';
import { TaskService } from '../../../task/task.service';
import { StudentMeeting } from '../../student-meeting';
import { MeetingService } from '../../meeting.service';
import { AuthService } from '../../../core/auth.service';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { MeetingReport } from '../../meeting-report';
import { MatDialog } from '@angular/material';
import { MeetingScheduleComponent } from '../meeting-schedule/meeting-schedule.component';

@Component({
  selector: 'app-meeting-detail',
  templateUrl: './meeting-detail.component.html',
  styleUrls: ['./meeting-detail.component.css']
})
export class MeetingDetailComponent implements OnInit {
  @Input('meeting') meeting: Meeting;
  listAllStd: Array<StudentMeeting>;
  isCancel: String;
  isCreateSchedule: String;
  topicTitle: String;
  isDiary: Boolean = false;
  aaa: Boolean = false;

  bookedSchedule = new TimeLocation();
  tempListStudent: Array<StudentMeeting> = [];
  meetingDiary: any;

  isPublicDiary: Boolean = true;

  constructor(public dialog: MatDialog, public snackBar: MatSnackBar, private meetingService: MeetingService, public authService: AuthService) {

  }

  ngOnInit() {
    this.isDiary = false;
    this.initTimeLocation();
    this.getTopicTitleFromID();
    this.findBookedSchedule();
    this.getAllStudentDoTopic();
    if (this.authService.isStudent()) {
      this.getMeetingDiary();
    }
  }

  getAllStudentDoTopic() {
    this.meetingService.getAllStudentDoTopic(this.meeting.topicID).subscribe(
      res => {
        if (res != null) {
          this.listAllStd = res;
          this.findBookedStudent();
        }
        else {
          console.log('ko co gia tri');
        }

      });
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
  }


  addTimeLocation() {
    const temp = new TimeLocation();
    var date = new Date(); 
    var JSONdate = date.toJSON();
    temp.meetingTime = JSONdate.slice(0,16);
    this.meeting.timeLocation.push(temp);
  }

  isCheckedSchedule(schedule: TimeLocation) {
    if (schedule.status == 1) {
      return '';
    } else {
      return null;
    }
  }

  cancelMeeting() {
    this.meetingService.cancelMeeting(this.meeting).subscribe(
      res => {
        if (res) {
          this.meeting.status = 2;
          this.snackBar.open("    Cancel Success!!!    ", "Close", {
            duration: 2000,
          });
        }
      }
    );
    this.isCancelMeeting();
  }

  isCancelMeeting() {
    if (this.isCancel == undefined) {
      this.isCancel = 'popup';
    } else {
      this.isCancel = undefined;
    }
  }

  isCreateScheduleMeeting() {
    if (this.isCreateSchedule == undefined) {
      this.isCreateSchedule = 'popup';
    } else {
      this.isCreateSchedule = undefined;
    }
  }

  findBookedSchedule() {
    if (this.meeting.timeLocation.length > 0) {
      for (let i = 0; i < this.meeting.timeLocation.length; i++) {
        if (this.meeting.timeLocation[i].status) {
          this.bookedSchedule = this.meeting.timeLocation[i];
        }
      }
    }
  }
  findBookedStudent() {
    if (this.meeting.student.length > 0) {
      for (let i = 0; i < this.listAllStd.length; i++) {
        this.listAllStd[i].meetingID = this.meeting.meetingID;
        for (let j = 0; j < this.meeting.student.length; j++) {
          if (this.meeting.student[j].name == this.listAllStd[i].name) {
            this.tempListStudent.push(this.listAllStd[i]);
          }
        }
      }
    }
  }

  showStudent() {
    console.log(this.meeting.student);
  }

  stdBookMeeting() {
    for (let i = 0; i < this.meeting.timeLocation.length; i++) {
      if (this.meeting.timeLocation[i].location == this.bookedSchedule.location
        && this.meeting.timeLocation[i].meetingTime == this.bookedSchedule.meetingTime) {
        this.meeting.timeLocation[i].status = 1;
      }
    }

    this.meeting.student = this.tempListStudent;


    this.meetingService.stdBookMeeting(this.meeting).subscribe(
      res => {
        if (res == "OK") {
          this.meeting.status = 1;
          this.snackBar.open("    Booking Success!!!    ", "Close", {
            duration: 2000,
          });
        }
      }
    );
  }

  getTopicTitleFromID() {
    this.meetingService.getTopicTitleFromID(this.meeting.topicID).subscribe(
      res => {
        this.topicTitle = res.title;
      }
    );
  }

  getPublicDiary(matExpansionPanel) {
    matExpansionPanel.toggle();
    this.isDiary = !this.isDiary;
    this.isPublicDiary = true;
  }
  getPersonalDiary(matExpansionPanel) {
    matExpansionPanel.toggle();
    this.isPublicDiary = false;
  }

  getMeetingContent(matExpansionPanel) {
    matExpansionPanel.toggle();
    this.isDiary = !this.isDiary;
    this.isPublicDiary = true;
  }

  getMeetingDiary() {
    this.meetingService.getMeetingDiary(this.meeting.meetingID).subscribe(
      res => {
        this.meetingDiary = res;
      }
    );
  }

  editMeetingDiary() {
    if (this.isPublicDiary) {
      let temp = new MeetingReport();
      temp.content = this.meeting.reportContent;
      temp.plan = this.meeting.reportPlan;
      temp.meetingID = this.meeting.meetingID;

      this.meetingService.editMeetingReport(temp).subscribe(
        res => {
          if (res == "OK") {
            this.snackBar.open("    Save Success!!!    ", "Close", {
              duration: 2000,
            });
          }
        }
      );
    } else {
      this.meetingService.editMeetingDiary(this.meetingDiary).subscribe(
        res => {
          if (res) {
            this.snackBar.open("    Edit Success!!!    ", "Close", {
              duration: 2000,
            });
          }
        }
      );
    }

  }

  openScheduleDialog(): void {
    const dialogRef = this.dialog.open(MeetingScheduleComponent, {
      width: '600px',
      data: { meeting: this.meeting }
    });

    // dialogRef.afterClosed().subscribe(result => {
    //   if (result.idTask != null) {
    //     result.taskID = result.idTask;
    //     this.taskListSv.taskList = this.taskListSv.taskList.map(data => {
    //       return data;
    //     });
    //   }
    // });
  }

  toDateTimeLocal(schedule: TimeLocation) {
    var ts = schedule.meetingTime;
    var t1 = Number(ts);
    var date = new Date(t1);
    var JSONdate = date.toJSON();
    schedule.meetingTime = JSONdate.slice(0, 16);
    return schedule;
  }

  initTimeLocation() {
    if (this.meeting.timeLocation.length > 0) {
      for (let i = 0; i < this.meeting.timeLocation.length; i++) {
        this.meeting.timeLocation[i] = this.toDateTimeLocal(this.meeting.timeLocation[i]);
      }
    } else {
      this.meeting.timeLocation = [];
      this.addTimeLocation();
    }
  }

}
