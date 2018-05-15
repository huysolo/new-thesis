import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {TaskContentComponent} from './components/task-content/task-content.component';
import { FormUploadComponent } from './components/upload/form-upload/form-upload.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {TaskChatgroupComponent} from './components/task-chatgroup/task-chatgroup.component';
import {TaskDetailComponent} from './components/task-detail/task-detail.component';
import {TaskCreateComponent} from './components/task-create/task-create.component';
import { ListUploadComponent } from './components/upload/list-upload/list-upload.component';
import { TaskDetailPageComponent } from './pages/task-detail-page/task-detail-page.component';

const taskRoutes: Routes = [
  {
    path: 'content',
    component: TaskContentComponent,
    pathMatch: 'full'
  },
  {
    path: 'detail',
    component: TaskDetailComponent,
  },
  {
    path: 'create',
    component: TaskCreateComponent,
  },
  {
    path: 'upload',
    component: FormUploadComponent,
    pathMatch: 'full'
  },
  {
    path: 'listupload',
    component: ListUploadComponent,
  },
  {
    path: 'chatgroup',
    component: TaskChatgroupComponent,
    pathMatch: 'full'
  }, {
    path: 'task-detail/:id',
    component: TaskDetailPageComponent,
    pathMatch: 'full'
  },
  {
    path: '',
    component: TaskListComponent,
    children: [
      {
        path: ':typ',
        component: TaskContentComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(taskRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class TaskRoutingModule { }
