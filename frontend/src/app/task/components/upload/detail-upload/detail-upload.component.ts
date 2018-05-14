import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-detail-upload',
  templateUrl: './detail-upload.component.html',
  styleUrls: ['./detail-upload.component.css']
})
export class DetailsUploadComponent implements OnInit {

  @Input() fileUpload: string;
  @Output() rmEmitter: EventEmitter<String> = new EventEmitter<String>();
  @Input() canRemove = false;
  constructor() {}

  ngOnInit() {
  }

  filename() {
    const path = this.fileUpload.substring(this.fileUpload.lastIndexOf('/') + 1);
    return (path.match(/[^.]+(\.[^?#]+)?/) || [])[0];
  }
  emitRemove() {
    this.rmEmitter.emit(this.fileUpload);
  }

}
