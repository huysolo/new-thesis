import { Component, OnInit } from '@angular/core';
import { SemesterService } from '../core/semester.service';
import { CommonService } from '../core/common.service';
import { Semester } from '../models/Semester';
import { LayoutService } from '../layout/layout.service';

@Component({
  selector: 'app-semester',
  templateUrl: './semester.component.html',
  styleUrls: ['./semester.component.css']
})
export class SemesterComponent implements OnInit {
  semesterEdit: Semester = new Semester();
  constructor(public semSv: SemesterService, public commonSv: CommonService, private layoutSv: LayoutService) {
    layoutSv.labelName = 'Semester';
   }
  ngOnInit() {

  }

  edit(sem: Semester) {
    this.semesterEdit.semesterNo = sem.semesterNo;
    this.semesterEdit.applyOpenDate = new Date(sem.applyOpenDate);
    this.semesterEdit.applyCloseDate = new Date(sem.applyCloseDate);
    this.semesterEdit.beginDate = new Date(sem.beginDate);
    this.semesterEdit.startDate = new Date(sem.startDate);
    this.semesterEdit.reviewDate = new Date(sem.reviewDate);
    this.semesterEdit.endDate = new Date(sem.endDate);
    this.semesterEdit.closeDate = new Date(sem.closeDate);
    this.semesterEdit.midtermReviewDate = new Date(sem.midtermReviewDate);

  }

  submitEdit() {
    this.commonSv.editSemester(this.semesterEdit).subscribe(data  => {
      this.semSv.getListSemster().forEach((sem, index, lst) => {
        if (sem.semesterNo === data.semesterNo) {
          lst[index] = data;
        }
      });
      this.semesterEdit = new Semester();
    });
  }

  resetSem() {
    this.semesterEdit = new Semester();

  }

}
