import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LoginService } from '../../pages/login/login.service';
import { Observable } from 'rxjs/Observable';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { ResourceLoader } from '@angular/compiler';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-avatar-form',
  templateUrl: './avatar-form.component.html',
  styleUrls: ['./avatar-form.component.css']
})
export class AvatarFormComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;

  fileUpload: Observable<String>;

  constructor(private userSv: LoginService, public snackBar: MatSnackBar, private authoSv: AuthService) {}

  ngOnInit() {
    this.reload();
  }

  reload() {
    this.fileUpload = this.userSv.getAvatar(this.authoSv.getUserId());
  }

  selectFile(event) {
    const file = event.target.files.item(0);
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
    this.currentFileUpload = this.selectedFiles.item(0);
    this.userSv.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
        this.openSnackBar('File is completely uploaded!', 'Close');
      }
      this.reload();
    });

    this.selectedFiles = undefined;
  }

}
