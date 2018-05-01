import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { MeetingCreateComponent } from './component/meeting-create/meeting-create.component';
import { MeetingDetailComponent } from './component/meeting-detail/meeting-detail.component';
import { MeetingContentComponent } from './component/meeting-content/meeting-content.component';

const meetingRoutes: Routes = [
  {
    path: 'content',
    component: MeetingContentComponent,
    pathMatch: 'full'
  },
  {
    path: 'detail',
    component: MeetingDetailComponent,
    pathMatch: 'full'
  },
  {
    path: 'create',
    component: MeetingCreateComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(meetingRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class MeetingRoutingModule { }

