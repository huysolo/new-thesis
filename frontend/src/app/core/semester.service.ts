import { Injectable, OnInit } from '@angular/core';
import { CommonService } from './common.service';
import { Semester } from '../models/Semester';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SemesterService {
  private listSemester: Semester[];
  private currentSemester: Semester;
  constructor(private commonSv: CommonService) {
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
    // if (semNo != this.currentSemester.semesterNo) {
    //   return false;
    // }
    return this.currentSemester != null &&
    semNo == this.currentSemester.semesterNo && this.checkTime(this.currentSemester.applyOpenDate, this.currentSemester.applyCloseDate);
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

  // getState(semNo){
  //   if(){
      
  //   }
  // }

}
