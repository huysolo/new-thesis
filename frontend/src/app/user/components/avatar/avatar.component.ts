import { Component, OnInit, Input } from '@angular/core';
import { LoginService } from '../../pages/login/login.service';
import { Observable } from 'rxjs/Observable';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrls: ['./avatar.component.css']
})
export class AvatarComponent implements OnInit {
  @Input() userId;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 }

  showFile = false;
  fileUpload: Observable<String>;

  constructor(private userSv: LoginService) {}

  ngOnInit() {
    this.fileUpload = this.userSv.getAvatar(this.userId);
  }




  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.userSv.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }

}
