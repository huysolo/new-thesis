import { StudentDoTask } from './student-do-task';

export class TaskInfo {
  title: String;
  description: String;
  deadline: String;
  student: Array<StudentDoTask>;

  public TaskInfo(title: String, des: String, deadline: String, student: Array<StudentDoTask>) {
    this.title = title;
    this.description = des;
    this.deadline = deadline;
    this.student = student;
  }
}
