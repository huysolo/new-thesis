import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TopicListComponent } from '../topic-list/topic-list.component';
import { Topic } from '../../../models/Topic';
import { TopicFormButtonComponent } from '../topic-form-button/topic-form-button.component';
import { TopicFormButtonDirective } from '../../directives/topic-form-button.directive';

@Component({
  selector: 'app-create-form-modal',
  templateUrl: './create-form-modal.component.html',
  styleUrls: ['./create-form-modal.component.css']
})
export class CreateFormModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<TopicFormButtonDirective>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    }

  ngOnInit() {
  }

  closeSuccess(topic: Topic) {
    this.dialogRef.close(topic);
  }

}
