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

  fileUpload: Observable<String>;

  constructor(private userSv: LoginService) {}

  ngOnInit() {
    this.fileUpload = this.userSv.getAvatar(this.userId);

  }





}
