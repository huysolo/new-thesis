import { Component, OnInit, Input, Output, EventEmitter, Inject, NgZone } from '@angular/core';
import {TimeLocation} from '../../time-location';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import {MeetingDetailComponent} from '../meeting-detail/meeting-detail.component';
import { Meeting } from '../../meeting';
import { MatSnackBar } from '@angular/material';
import { MeetingService } from '../../meeting.service';


@Component({
  selector: 'app-meeting-schedule',
  templateUrl: './meeting-schedule.component.html',
  styleUrls: ['./meeting-schedule.component.css']
})
export class MeetingScheduleComponent implements OnInit {
  meeting: Meeting;
  constructor(private snackBar: MatSnackBar,private meetingService: MeetingService , public dialogRef: MatDialogRef<MeetingDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.meeting = data.meeting;
    }

  ngOnInit() {
    this.initTimeLocation();
  }

  removeMeeting(i: number) {
    this.meeting.timeLocation.splice(i, 1);
  }

  addTimeLocation() {
    const temp = new TimeLocation();
    var date = new Date(); 
    var JSONdate = date.toJSON();
    temp.meetingTime = JSONdate.slice(0,16);
    this.meeting.timeLocation.push(temp);
  }

  initTimeLocation() {
    if (this.meeting.timeLocation.length == 0) {
      this.meeting.timeLocation = [];
      this.addTimeLocation();
    }
  }

  profCreateScheduleMeeting() {
    this.meetingService.profCreateScheduleMeeting(this.meeting).subscribe(
      res => {
        if (res == "OK") {
          this.snackBar.open("    Schedule Success!!!    ", "Close", {
            duration: 2000,
          });
          this.dialogRef.close(this.meeting);
        } else if(res == "CONFLICT") {
          this.snackBar.open("Ops!!! Student have booked this Meeting. You can't reschedule. Reload page to see the change.", "Close", {
            duration: 30000,
          });
        }
        
      }
    );
  }

}
