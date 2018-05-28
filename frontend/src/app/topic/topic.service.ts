import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';
import { AuthService } from '../core/auth.service';
import { StudentDoTask } from '../task/components/student-do-task';
import { Review } from '../models/Review';

@Injectable()
export class TopicService {
  public pageList: Number[] = [];
  public selectedPage = 1;
  public pageSize = 5;
  requestType: string;
  constructor(private http: HttpClient, private authoSv: AuthService) {
    this.requestType = 'recent';
  }
  appliedTopic: Topic;
  topicLst: Observable<Topic[]>;
  private topicUrl = 'http://localhost:8080/topic/';
  private topicListUrl = this.topicUrl + 'listTopic';
  private topicRecentListUrl = this.topicUrl + 'recentTopics';
  private topicDetailUrl = this.topicUrl + 'topicDetail';
  private topicCreatelUrl = this.topicUrl +  'create';
  private topicListSizeUrl = this.topicUrl +  'listTopicSize';
  private topicApplyUrl = this.topicUrl +  'apply';
  private topicAppliedUrl = this.topicUrl +  'appliedTopic';
  private topicRejectUrl = this.topicUrl +  'reject';
  private topicListDraftUrl = this.topicUrl +  'listDraft';
  private topicPublishtUrl = this.topicUrl +  'publish';
  private topicListReviewUrl = this.topicUrl +  'listReview';
  private topicStudentUrl = this.topicUrl + 'student';
  private topicCountUrl = this.topicUrl + 'topicCount';
  private appliedListUrl = this.topicUrl + 'appliedList';
  private listProfTopicUrl = this.topicUrl + 'listProfTopic';
  private reviewsUrl = this.topicUrl + 'reviews';

  private stdTopic = this.topicUrl + 'stdgetcurrtopic';
  private profCurrTopic = this.topicUrl + 'profgetcurrappliedtopic';
  /**
   * reject
   */
  public reject(topicId: Number) {
    return this.http.post<any>(this.topicRejectUrl, topicId).map(res => {
      return res;
    });
  }
  /**
   * getAppliedTopic
   */
  public getAppliedTopic(params: HttpParams): Observable<Topic> {
    return this.http.get<Topic>(this.topicAppliedUrl, {params: params});
  }
  /**
   * getListTopic
   * Get List Topic for Current Semester
   */
  public getListTopic(params: HttpParams): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.topicRecentListUrl, {params: params});
  }

  public getListRecentTopic() {
    return this.http.get<Topic[]>(this.topicRecentListUrl);
  }

  /**
   * getTopicDetail
   */
  public getTopicDetail(id: Number): Observable<TopicDetail> {
    return this.http.get<TopicDetail>(this.topicDetailUrl + '?topid=' + id);
  }

  /**
   * getListTopicBySemesterAndProf
  */
  public getListTopicBySemesterAndProf(params: HttpParams): Observable<Topic[]>  {
    if (this.requestType === 'recent') {
      return this.getListTopic(params);
    } else if (this.requestType === 'history') {
      return this.http.get<Topic[]>(this.topicListUrl, {params: params});
    } else if (this.requestType === 'draft') {
      return this.http.get<Topic[]>(this.topicListDraftUrl);
    } else {
      return this.getListReview(params);
    }
  }

  /**
   * getListTopic
   */
  public getAppliedList() {
    return this.http.get<Topic[]>(this.appliedListUrl);
  }

  public createTopic(topicDetail: TopicDetail) {
    return this.http.post<any>(this.topicCreatelUrl, topicDetail)
      .map(res => {
        return res;
      });
  }

  /**
   * applyToTopic
   */
  public applyToTopic(topicId: Number) {
    return this.http.post<any>(this.topicApplyUrl, topicId).map(res => {
      return res;
    });
  }

  /**
   * publish
   */
  public publishTopic(topicId: Number) {
    return this.http.post<Topic>(this.topicPublishtUrl, topicId).map(res => {
      return res;
    });
  }

  public setPage(length: number) {
    const pageLength = length / this.pageSize + (length % this.pageSize > 0 ? 1 : 0);
    this.pageList = new Array<Number>();
    this.selectedPage = 1;
    for (let i = 1; i <= pageLength; i++) {
      this.pageList.push(i);
    }
  }

  public getListReview(params) {
    return this.http.get<Topic[]>(this.topicListReviewUrl, {params: params});
  }

  public getListTopicReview(semno, submitted, guide) {
    let params = new HttpParams().append('submitted', submitted).append('guide', guide);
    if (semno != null) {
      params = params.append('semno', semno);
    }
    return this.http.get<Topic[]>(this.topicListReviewUrl, {params: params});
  }

  public deleteTopic(id) {
    const params = new HttpParams().append('topid', id);
    return this.http.delete<any>(this.topicUrl, {params: params});
  }

  getAllStudentDoTopic(idTopic) {
    if (idTopic == null) {
      return this.http.get<StudentDoTask[]>(this.topicStudentUrl);
    }
    const params = new HttpParams().append('id', idTopic);
    return this.http.get<StudentDoTask[]>(this.topicStudentUrl, {params: params});
  }

  countTopic() {
    return this.http.get<Number>(this.topicCountUrl);
  }

  getTopicById(id) {
    return this.http.get<Topic>(this.topicUrl + '?id=' + id);
  }

  getListTopicForProf(semNo = null) {
    let param = new HttpParams();
    if (semNo != null) {
      param = param.append('semNo', semNo);
    }
    return this.http.get<Topic[]>(this.listProfTopicUrl, {params: param});
  }

  getReviewsByIdTopic(id) {
    return this.http.get<Review[]>(this.reviewsUrl + '?id=' + id);
  }

  getListDraft() {
    return this.http.get<Topic[]>(this.topicListDraftUrl);
  }

  stdGetCurrTopic() {
    return this.http.get<Topic>(this.stdTopic);
  }

  profGetCurrAppliedTopic() {
    return this.http.get<Topic[]>(this.profCurrTopic);
  }

}
