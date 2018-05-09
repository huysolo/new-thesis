import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Meeting } from './meeting';

@Injectable()
export class MeetingService {

  constructor(private httpClient: HttpClient) { }

  getAllStudentDoTopic() {
    const url = `http://localhost:8080/meeting/getallstddotopic`;
    return this.httpClient.get<any>(url);
  }

  createMeeting(createMeeting: Meeting) {
    const loginUrl = `http://localhost:8080/meeting/createmeeting`;
    return this.httpClient.post<any>(loginUrl, createMeeting);
  }

  getListMeetingFromTopicID(topicid: number) {
    const loginUrl = `http://localhost:8080/meeting/getlistmeeting`;
    return this.httpClient.get<any>(loginUrl + '?topicid=' + topicid);
  }

  editMeeting(schedule) {
    const loginUrl = `http://localhost:8080/meeting/editmeeting`;
    return this.httpClient.post<any>(loginUrl,schedule);
  }

  cancelMeeting(meeting) {
    const loginUrl = `http://localhost:8080/meeting/cancelmeeting`;
    return this.httpClient.post<any>(loginUrl, meeting);
  }

}
