import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Standard } from './models/Standard';
import { Observable } from 'rxjs/Observable';
import { ReviewTopic } from './models/ReviewTopic';
import { Review } from './models/Review';

@Injectable()
export class StandardService {
  private standardUrl = 'http://localhost:8080/topic/';
  private currentStandardUrl = this.standardUrl + 'standard';
  private reviewUrl = this.standardUrl + 'review';

  constructor(private http: HttpClient) { }

  getCurrentSemStandard(): Observable<Standard[]> {
    return this.http.get<Standard[]>(this.currentStandardUrl);
  }

  postStandard(standard: Standard): Observable<Standard> {
    return this.http.post<Standard>(this.currentStandardUrl, standard);
  }

  postReview(reviewTopic: ReviewTopic) {
    return this.http.post<Review>(this.reviewUrl, reviewTopic);
  }

  deleteStandard(standardId: Number) {
    const params = new HttpParams().append('id', standardId.toString());
    return this.http.delete<Number>(this.currentStandardUrl, {params: params});
  }

}
