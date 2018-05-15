import { Pipe, PipeTransform } from '@angular/core';
import { TaskService } from './task.service';

@Pipe({
  name: 'studentTask'
})
export class StudentTaskPipe implements PipeTransform {

  constructor(taskSv: TaskService) {

  }
  transform(value: any, args?: any): any {
    return null;
  }

}
