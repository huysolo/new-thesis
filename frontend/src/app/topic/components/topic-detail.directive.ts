import { Directive, HostListener, Input } from '@angular/core';
import { MatDialog } from '@angular/material';
import { TopicService } from '../topic.service';
import { TopidDetailComponent } from './topid-detail/topid-detail.component';
import { CommonService } from '../../core/common.service';

@Directive({
  selector: '[appTopicDetail]'
})
export class TopicDetailDirective {

  @Input() idTopic;
  constructor(public dialog: MatDialog, private topicSv: TopicService, private commonSv: CommonService) { }

  @HostListener('click')
  getTopicId(): void {
    this.topicSv.getTopicDetail(this.idTopic).subscribe(rs => {
      const dialogRef = this.dialog.open(TopidDetailComponent, {
        width: '500px',
        data: {
          topicDetail: rs,
        }
      });
    });
  }

}


