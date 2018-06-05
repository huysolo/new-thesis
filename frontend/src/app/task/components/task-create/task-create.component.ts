import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit, Input, Output, EventEmitter, Inject, NgZone } from '@angular/core';
import { TaskService } from '../../task.service';
import { StudentDoTask } from '../student-do-task';
import { TaskInfo } from '../task-info';
import { TaskContentComponent } from '../task-content/task-content.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


@Component({
  selector: 'app-task-create',
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent implements OnInit {
  crttaskForm: FormGroup;
  listAllStd: Array<StudentDoTask>;
  listStdDoTask: Array<Number> = [];
  taskInfo: TaskInfo;
  task = 'fdgnbkxcvzhknjl;cvxza;mkj/,xzcvm/.,xbcvsz/.,xzcvdg.m,/xc,vz.m/vxzc';


  // @Output('checkCreate') isCreate = new EventEmitter<Boolean>();
  // @Output('addNewTask') newTask = new EventEmitter<any>();

  constructor(private fb: FormBuilder, private zone: NgZone,
    private taskService: TaskService, public dialogRef: MatDialogRef<TaskContentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.listAllStd = data.students;
      this.taskInfo = new TaskInfo();
  }
  getValue(e, st: StudentDoTask) {
    st.selected = e;
  }

  ngOnInit() {
  }



  submit() {
    this.zone.run(() => {
      this.taskInfo.studentIdList = [];
      this.listAllStd.filter(data => {
        return data.selected === true;
      }).forEach(key => {
        this.taskInfo.studentIdList.push(key.studentId);
      });
      this.taskService.createtask(this.taskInfo).subscribe(data => {
      this.dialogRef.close(data);

      });
    });
  }

}
