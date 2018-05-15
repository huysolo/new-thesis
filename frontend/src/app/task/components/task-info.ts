import { StudentDoTask } from './student-do-task';

export class TaskInfo {
  title: String;
  description: String;
  deadline: String;
  studentIdList: Array<Number>;

  public TaskInfo(title: String, des: String, deadline: String, studentIdList: Array<Number>) {
    this.title = title;
    this.description = des;
    this.deadline = deadline;
    this.studentIdList = studentIdList;
  }
}
