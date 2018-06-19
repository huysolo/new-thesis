import { Injectable, OnInit } from '@angular/core';
import { CommonService } from './common.service';
import { Semester } from '../models/Semester';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SemesterService {
  private listSemester: Semester[];
  private currentSemester: Semester;
  constructor(private commonSv: CommonService) {
    this.init().subscribe(data => {

    });
  }

  init() {
    return this.commonSv.getListSemester().map(semList => {

      this.listSemester = semList;
      semList.forEach(sem => {
        if (this.isOpen(sem)) {
          this.currentSemester = sem;
        }
      });
      return semList;
    });
  }

  getSemesterBySemId(semNo: number) {
    return this.listSemester.find(sem => sem.semesterNo === semNo);
  }

  getListSemster() {
    return this.listSemester;
  }

  getCurrrentSem() {
    return this.currentSemester;
  }

  canApply(semNo) {
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && this.checkTime(this.currentSemester.applyOpenDate, this.currentSemester.applyCloseDate);
  }

  canApplyToCurrentSem() {
    return this.currentSemester != null && this.checkTime(this.currentSemester.applyOpenDate, this.currentSemester.applyCloseDate);
  }

  canUse(semNo) {

    // if (semNo != this.currentSemester.semesterNo) {
    //   return false;
    // }
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && this.checkTime(this.currentSemester.startDate, this.currentSemester.endDate);
  }

  isOpen(d: Semester) {
    return this.checkTime(d.applyOpenDate, d.closeDate);
  }

  checkTime(st, en) {
    const currentTime = new Date().getTime();
    return st < currentTime && en > currentTime;
  }

  isStartDoTask(semNo){
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && 
    this.checkTime(this.currentSemester.startDate, this.currentSemester.midtermReviewDate);
  }

  isInDoTask(semNo) {
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo &&
    this.checkTime(this.currentSemester.startDate, this.currentSemester.endDate);
  }

  isMiddleReview(semNo) {
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && 
    this.checkTime(this.currentSemester.midtermReviewDate, this.currentSemester.endDate);
  }

  isCurrentFinalReview() {
    return this.checkTime(this.currentSemester.reviewDate, this.currentSemester.closeDate);
  }

  isFinalReview(semNo) { 
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && 
    this.checkTime(this.currentSemester.reviewDate, this.currentSemester.closeDate);
  }

  getState(semNo){
    if(this.canApply(semNo)){
      return 0;
    } else if (this.isStartDoTask(semNo)){
      return 1;
    } else if(this.isMiddleReview(semNo)) {
      return 2;
    } else if(this.isFinalReview){
      return 3;
    }
  }

}
