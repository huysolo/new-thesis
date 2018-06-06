import { StudentDoTask } from './student-do-task';

export class TaskInfo {
  taskID: number;
  title: String;
  description: String;
  deadline: Date;
  studentIdList: Array<Number>;

  public TaskInfo(title: String, des: String, deadline: Date, studentIdList: Array<Number>) {
    this.title = title;
    this.description = des;
    this.deadline = deadline;
    this.studentIdList = studentIdList;
  }
}
