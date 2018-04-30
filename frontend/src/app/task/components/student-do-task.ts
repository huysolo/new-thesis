export class StudentDoTask {
  stdName: String;
  archive: String;
  uploadDate: Date;

  public StudentDoTask(name: String, archive: String, uploadDate: Date ) {
    this.stdName = name;
    this.archive = archive;
    this.uploadDate = uploadDate;
  }
}
