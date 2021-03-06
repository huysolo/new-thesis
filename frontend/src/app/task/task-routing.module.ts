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
import { ChatboxComponent } from './components/chatbox/chatbox.component';

const taskRoutes: Routes = [
  {
    path: 'content',
    component: TaskContentComponent,
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
  },
  {
    path: 'listupload',
    component: ListUploadComponent,
  },
  {
    path: 'chatgroup',
    component: TaskChatgroupComponent,
  }, {
    path: 'task-detail/:id',
    component: TaskDetailPageComponent,
  },{
    path: 'chat',
    component: ChatboxComponent,
  },
  {
    path: 'list',
    component: TaskListComponent,
    children: [{
      path: ':id',
      component: TaskContentComponent
    }]
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
