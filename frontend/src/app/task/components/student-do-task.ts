export class StudentDoTask {
  stdName: String;
  studentId: number;
  selected: boolean;
  teamlead: boolean;
  userId: number;
  public StudentDoTask(name: String, studentId ) {
    this.stdName = name;
    this.studentId = studentId;
    this.selected = false;
  }
}
