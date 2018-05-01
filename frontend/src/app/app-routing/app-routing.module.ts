import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from '../main-page/main-page.component';
import {LoginComponent} from '../user/pages/login/login.component';
import { AuthGuardUserService } from '../core/auth-guard-user.service';
import {LayoutComponent} from '../layout/layout.component';

const appRoutes: Routes = [
  {
    path: 'topic',
    loadChildren: 'app/topic/topic.module#TopicModule',
    canActivate: [AuthGuardUserService]
  },
  {
    path: 'user',
    loadChildren: 'app/user/user.module#UserModule'
  },
  {
    path: 'task',
    loadChildren: 'app/task/task.module#TaskModule'
  },
  {
    path: 'meeting',
    loadChildren: 'app/meeting/meeting.module#MeetingModule'
  },
  {
    path: 'layout',
    component: LayoutComponent
  }
  ,
  {
    path: '',
    component: MainPageComponent,
    canActivate: [AuthGuardUserService]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
