import { Directive, Output, EventEmitter, Input, HostListener } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DisapproveComponent } from './disapprove/disapprove.component';
import { Topic } from '../../models/Topic';
import { TopicService } from '../topic.service';
import { Disapprove } from '../../models/Disapprove';

@Directive({
  selector: '[appDisapprove]'
})
export class DisapproveDirective {

  @Output() disapprove = new EventEmitter<Topic>();
  @Input() idTopic;
  constructor(public dialog: MatDialog, private topicSv: TopicService) { }


  @HostListener('click')
  openDialog(): void {
    const dialogRef = this.dialog.open(DisapproveComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != null && result !== '') {
        const disapprove = new Disapprove(this.idTopic, result);
        this.topicSv.disapproveTopic(disapprove).subscribe(dt => {
          this.disapprove.emit(dt);
        });
      }
    });
  }

}
