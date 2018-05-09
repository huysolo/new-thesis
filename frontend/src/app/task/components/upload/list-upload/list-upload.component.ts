import {Component, OnInit, Input} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UploadFileService } from '../../../upload-file.service';

@Component({
  selector: 'app-list-upload',
  templateUrl: './list-upload.component.html',
  styleUrls: ['./list-upload.component.css']
})
export class ListUploadComponent implements OnInit {
  @Input() id;
  @Input() ver;
  showFile = false;
  fileUploads: Observable<string[]>;
  currentVersion: number;
  listVersion: Observable<number[]> = new Observable<number[]>();

  constructor(public uploadService: UploadFileService) {
  }

  ngOnInit() {
    this.currentVersion = this.ver;
    this.listVersion = this.uploadService.getListVersion(this.ver);
  }

  showFiles(enable: boolean) {
    this.showFile = enable;

    if (enable) {
      this.fileUploads = this.uploadService.getFiles(this.id, this.currentVersion);
    }
  }

  deleteFile(name) {
      this.uploadService.deleteFile(this.id, this.currentVersion, name).subscribe(s => {
        this.fileUploads = this.uploadService.getFiles(this.id, this.currentVersion);
      });

  }

  reloadListVersion(event) {
    this.listVersion = this.listVersion.map(lst => {
      lst.push(event);
      return lst;
    });
  }

  changeVersion(v) {
    this.currentVersion = v;
    this.showFiles(true);
  }

}
