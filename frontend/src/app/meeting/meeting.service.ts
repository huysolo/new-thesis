import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MeetingService {

  constructor(private httpClient: HttpClient) { }

  getAllStudentDoTopic() {
    const url = `http://localhost:8080/meeting/getallstddotopic`;
    return this.httpClient.get<any>(url);
  }

}

