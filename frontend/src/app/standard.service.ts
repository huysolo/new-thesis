import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Standard } from './models/Standard';
import { Observable } from 'rxjs/Observable';
import { ReviewTopic } from './models/ReviewTopic';
import { Review } from './models/Review';
import { TopicSemStandard } from './models/TopicSemStandard';

@Injectable()
export class StandardService {
  private standardUrl = 'http://localhost:8080/topic/';
  private standardListUrl = this.standardUrl + 'standard';
  private reviewUrl = this.standardUrl + 'review';
  private reviewCouncilUrl = this.standardUrl + 'reviewCouncil';
  private generalStandardUrl = this.standardUrl + 'generalStandard';
  private standardAndgeneralUrl = this.standardUrl + 'standardAndGeneral';

  constructor(private http: HttpClient) { }

  getCurrentSemStandard(): Observable<Standard[]> {
    return this.http.get<Standard[]>(this.standardListUrl);
  }

  postStandard(standard: Standard): Observable<Standard> {
    return this.http.post<Standard>(this.standardListUrl, standard);
  }

  postReview(reviewTopic: ReviewTopic) {
    return this.http.post<Review>(this.reviewUrl, reviewTopic);
  }

  deleteStandard(standardId: Number) {
    const params = new HttpParams().append('id', standardId.toString());
    return this.http.delete<Number>(this.standardListUrl, {params: params});
  }

  getReview(topicId: Number, profId = null) {
    let params = new HttpParams().append('id', topicId.toString());
    if (profId != null) {
      params = params.append('profId', profId);
    }
    return this.http.get<TopicSemStandard[]>(this.reviewUrl, {params: params});
  }

  getReviewCouncil(topicId: Number, councilId) {
    const params = new HttpParams().append('id', topicId.toString()).append('idCouncil', councilId);

    return this.http.get<TopicSemStandard[]>(this.reviewCouncilUrl, {params: params});
  }

  getListGeneralStandard(semNo) {
    const params = new HttpParams().append('semNo', semNo);
    if (semNo != null) {
      return this.http.get<Standard[]>(this.generalStandardUrl, {params: params});
    }
    return this.http.get<Standard[]>(this.generalStandardUrl);
  }

  getListStandardAndGeneral() {
    return this.http.get<Standard[]>(this.standardAndgeneralUrl);
  }

}
