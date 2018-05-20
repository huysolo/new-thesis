import { Directive, HostListener } from '@angular/core';

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { MatDialog } from '@angular/material';
import { TopicDetail } from '../../models/TopicDetail';
import { Topic } from '../../models/Topic';
import { CreateFormModalComponent } from '../components/create-form-modal/create-form-modal.component';
import { TopicService } from '../topic.service';
@Directive({
  selector: '[appTopicFormButton]'
})
export class TopicFormButtonDirective {

  @Input() draft = false;
  @Input() idTop = null;
  @Output() success = new EventEmitter<Topic>();

  constructor(public dialog: MatDialog, private topicSv: TopicService) { }

  @HostListener('click')
  openDialog(e): void {
    let dialogRef;
    if (this.idTop != null) {
      this.topicSv.getTopicDetail(this.idTop).subscribe(data => {
        dialogRef = this.dialog.open(CreateFormModalComponent, {
          width: '500px',
          data: {
            draft: this.draft,
            topicDetail: data,
           }
        });
        dialogRef.afterClosed().subscribe(result => {
          this.success.emit(result);
        });
      });
    } else {
      dialogRef = this.dialog.open(CreateFormModalComponent, {
        width: '500px',
        data: {
          draft: this.draft,
          topicDetail: new TopicDetail(),
         }
      });
      dialogRef.afterClosed().subscribe(result => {
        this.success.emit(result);
      });
    }


  }


}
