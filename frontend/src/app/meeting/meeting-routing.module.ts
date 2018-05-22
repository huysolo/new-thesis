import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { MeetingCreateComponent } from './component/meeting-create/meeting-create.component';
import { MeetingDetailComponent } from './component/meeting-detail/meeting-detail.component';
import { MeetingContentComponent } from './component/meeting-content/meeting-content.component';
import { MeetingListComponent } from './component/meeting-list/meeting-list.component';
import { MeetingDetailPageComponent } from './component/meeting-detail-page/meeting-detail-page.component';

const meetingRoutes: Routes = [
  {
    path: 'content',
    component: MeetingContentComponent,
  },
  {
    path: 'detail',
    component: MeetingDetailComponent,
  },
  {
    path: 'create',
    component: MeetingCreateComponent,
  },
  {
    path: 'list',
    component: MeetingListComponent,
    children: [
      {
        path: ':typ',
        component: MeetingContentComponent
      }
    ]
  }, {
    path: 'meeting-detail/:id',
    component: MeetingDetailPageComponent,
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

