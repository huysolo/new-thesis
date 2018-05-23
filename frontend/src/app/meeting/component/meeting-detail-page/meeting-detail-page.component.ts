import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {MeetingService} from '../../meeting.service';

@Component({
  selector: 'app-meeting-detail-page',
  templateUrl: './meeting-detail-page.component.html',
  styleUrls: ['./meeting-detail-page.component.css']
})
export class MeetingDetailPageComponent implements OnInit {
  meeting: any;
  constructor(private meetingService: MeetingService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.getMeetingFromMeetingID(param['id']);
     
    });
  }

  getMeetingFromMeetingID(meetingID){
    this.meetingService.getMeetingFromMeetingID(meetingID).subscribe(
      res => {
        this.meeting = res;
      }
    );
  }
}

