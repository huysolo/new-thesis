import { Injectable, OnInit } from '@angular/core';
import { CommonService } from './common.service';
import { Semester } from '../models/Semester';

@Injectable()
export class SemesterService {
  private listSemester: Semester[];
  private currentSemester: Semester;
  constructor(private commonSv: CommonService) {
    this.commonSv.getListSemester().subscribe(semList => {
      this.listSemester = semList;
      semList.forEach(sem => {
        console.log(sem);
        if (this.checkOpen(sem)) {
          this.currentSemester = sem;
        }
      });
    });
  }


  getCurrrentSem() {
    return this.currentSemester;
  }

  canApply() {
    return this.checkTime(this.currentSemester.applyOpenDate, this.currentSemester.applyCloseDate);
  }

  checkOpen(d: Semester) {
    return this.checkTime(d.applyOpenDate, d.closeDate);
  }

  checkTime(st: Date, en: Date) {
    const currentTime = new Date();
    return st < currentTime && en > currentTime;
  }

}
