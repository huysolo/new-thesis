import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { UploadFileService } from '../../../upload-file.service';
import { TaskInfo } from '../../task-info';

@Component({
  selector: 'app-form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./form-upload.component.css']
})
export class FormUploadComponent implements OnInit {

  @Input() id = 1;
  @Input() currentVersion = 2;
  postVersion: number;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  constructor(public uploadService: UploadFileService) { }

  ngOnInit() {
    this.postVersion = this.currentVersion;
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
  changeVersion(event) {
    this.postVersion = event;
  }

  newversion() {
    this.uploadService.newVersion(1).subscribe(data => {
      console.log(data);
    });
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload, this.id, this.postVersion).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }

}
