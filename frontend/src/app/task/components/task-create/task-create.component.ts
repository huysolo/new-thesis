import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TaskService } from '../../task.service';
import { StudentDoTask } from '../student-do-task';
import { TaskInfo } from '../task-info';


@Component({
  selector: 'app-task-create',
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent implements OnInit {
  crttaskForm: FormGroup;
  listAllStd: Array<any>;
  listStdDoTask: Array<StudentDoTask> = [];
  

  @Output('checkCreate') isCreate = new EventEmitter<Boolean>();
  @Output('addNewTask') newTask = new EventEmitter<any>();

  constructor(private fb: FormBuilder, private taskService: TaskService) { 
    this.crttaskForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      deadline: ['2017-11-16T20:00', Validators.required]
    });
  }

  ngOnInit() {
    this.getAllStudentDoTopic();
  }

  getAllStudentDoTopic() {
    this.taskService.getAllStudentDoTopic().subscribe(
      res => {
        this.listAllStd = res;
      });
  }

  isCreateTask(){
   this.isCreate.emit(false);
  }

  checked(list): void {
    if (list.class === undefined) {
      list.class = 'checked';
      const temp = <StudentDoTask>({ stdName: list.stdName, archive: null, uploadDate: null });
      this.listStdDoTask.push(temp);
    } else {
      list.class = undefined;
      for (let i = 0; i < this.listStdDoTask.length; i++) {
        if (this.listStdDoTask[i].stdName === list.stdName) {
          this.listStdDoTask.splice(i, 1);
        }
      }
    }
  }

  createtask(form) {
    const temp = <TaskInfo>({ title: form.title, description: form.description, deadline: form.deadline, student: this.listStdDoTask });
    this.taskService.createtask(temp).subscribe(
      res => {
        this.newTask.emit(res);
      }
    );
  }

}
