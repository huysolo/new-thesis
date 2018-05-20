import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TopicDetail } from '../../../models/TopicDetail';
import { Topic } from '../../../models/Topic';
import { MatDialog } from '@angular/material';
import { CreateFormModalComponent } from '../create-form-modal/create-form-modal.component';

@Component({
  selector: 'app-topic-form-button',
  templateUrl: './topic-form-button.component.html',
  styleUrls: ['./topic-form-button.component.css']
})
export class TopicFormButtonComponent implements OnInit {
  @Input() draft = false;
  @Input() topicDetail = new TopicDetail();
  @Output() success = new EventEmitter<Topic>();

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateFormModalComponent, {
      width: '500px',
      data: {
        draft: this.draft,
        topicDetail: this.topicDetail,
       }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.success.emit(result);
    });
  }

}
