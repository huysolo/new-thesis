import { Injectable, OnInit } from '@angular/core';
import { CommonService } from './common.service';
import { Semester } from '../models/Semester';

@Injectable()
export class SemesterService {
  private listSemester: Semester[];
  private currentSemester: Semester;
  constructor(private commonSv: CommonService) {
  }

  init() {
    this.commonSv.getListSemester().subscribe(semList => {
      this.listSemester = semList;
      semList.forEach(sem => {
        if (this.isOpen(sem)) {
          this.currentSemester = sem;
        }
      });
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

  canApply(d: Semester) {
    return this.checkTime(d.applyOpenDate, d.applyCloseDate);
  }

  canUse(d: Semester) {
    return this.checkTime(d, d.applyCloseDate);
  }

  isOpen(d: Semester) {
    return this.checkTime(d.applyOpenDate, d.closeDate);
  }

  checkTime(st, en) {
    const currentTime = new Date().getTime();
    return st < currentTime && en > currentTime;
  }

}
