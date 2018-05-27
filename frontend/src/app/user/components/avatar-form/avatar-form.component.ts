import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../pages/login/login.service';
import { Observable } from 'rxjs/Observable';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-avatar-form',
  templateUrl: './avatar-form.component.html',
  styleUrls: ['./avatar-form.component.css']
})
export class AvatarFormComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 }

  fileUpload: Observable<String>;

  constructor(private userSv: LoginService, public snackBar: MatSnackBar) {}

  ngOnInit() {
  }


  selectFile(event) {
    const file = event.target.files.item(0);
    console.log(file);

    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      this.openSnackBar('Invalid Format', 'Close');
    }
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.userSv.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        this.openSnackBar('File is completely uploaded!', 'Close');
      }
    });

    this.selectedFiles = undefined;
  }

}
