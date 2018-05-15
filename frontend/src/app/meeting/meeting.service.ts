import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Meeting } from './meeting';

@Injectable()
export class MeetingService {

  constructor(private httpClient: HttpClient) { }

  getAllStudentDoTopic(topicID) {
    const url = `http://localhost:8080/meeting/getallstddotopic`;
    return this.httpClient.get<any>(url + '?topicID=' + topicID);
  }

  createMeeting(createMeeting: Meeting) {
    const loginUrl = `http://localhost:8080/meeting/createmeeting`;
    return this.httpClient.post<any>(loginUrl, createMeeting);
  }

  getListMeetingFromTopicID(topicid: number) {
    const loginUrl = `http://localhost:8080/meeting/getlistmeeting`;
    return this.httpClient.get<any>(loginUrl + '?topicid=' + topicid);
  }

  profGetRecenMeeting() {
    const loginUrl = `http://localhost:8080/meeting/profgetrecentmeeting`;
    return this.httpClient.get<any>(loginUrl);
  }

  profGetHistoryMeeting() {
    const loginUrl = `http://localhost:8080/meeting/profgethistorymeeting`;
    return this.httpClient.get<any>(loginUrl);
  }

  stdGetListRecentMeeting(){
    const loginUrl = `http://localhost:8080/meeting/stdgetrecentmeeting`;
    return this.httpClient.get<any>(loginUrl);
  }

  stdGetListHistoryMeeting(){
    const loginUrl = `http://localhost:8080/meeting/stdgethistorymeeting`;
    return this.httpClient.get<any>(loginUrl);
  }

  getMeetingDiary(meetingID){
    const loginUrl = `http://localhost:8080/meeting/getmeetingdiary`;
    return this.httpClient.get<any>(loginUrl + '?meetingID=' + meetingID);
  }

  editMeetingDiary(meetingDiary){
    const loginUrl = `http://localhost:8080/meeting/editmeetingdiary`;
    return this.httpClient.post<any>(loginUrl, meetingDiary);
  }

  editMeeting(schedule) {
    const loginUrl = `http://localhost:8080/meeting/editmeeting`;
    return this.httpClient.post<any>(loginUrl,schedule);
  }

  profCreateScheduleMeeting(meeting) {
    const loginUrl = `http://localhost:8080/meeting/createschedulemeeting`;
    return this.httpClient.post<any>(loginUrl, meeting);
  }

  stdBookMeeting(meeting) {
    const loginUrl = `http://localhost:8080/meeting/stdbookmeeting`;
    return this.httpClient.post<any>(loginUrl, meeting);
  }

  cancelMeeting(meeting) {
    const loginUrl = `http://localhost:8080/meeting/cancelmeeting`;
    return this.httpClient.post<any>(loginUrl, meeting);
  }

  getTopicTitleFromID(topicID: number) {
    const loginUrl = `http://localhost:8080/meeting/gettopicfromid`;
    return this.httpClient.get<any>(loginUrl + '?topicID=' + topicID);
  }

}
