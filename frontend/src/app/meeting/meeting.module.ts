import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MeetingRoutingModule} from './meeting-routing.module';
import { FormsModule } from '@angular/forms';

import {MeetingService} from './meeting.service';

import { MeetingCreateComponent } from './component/meeting-create/meeting-create.component';
import { MeetingDetailComponent } from './component/meeting-detail/meeting-detail.component';
import { MeetingContentComponent } from './component/meeting-content/meeting-content.component';

@NgModule({
  imports: [
    CommonModule,
    MeetingRoutingModule,
    FormsModule
  ],
  declarations: [MeetingCreateComponent, MeetingDetailComponent, MeetingContentComponent],
  providers: [MeetingService]
})
export class MeetingModule { }

