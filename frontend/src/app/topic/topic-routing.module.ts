import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { TopicDetail } from '../models/TopicDetail';
import { AuthTopicGuardService } from './auth-topic-guard.service';
import { CreateTopicComponent } from './components/create-topic/create-topic.component';
import { ReviewTopicComponent } from './pages/review-topic/review-topic.component';
import { ReviewTopicPageComponent } from './pages/review-topic-page/review-topic-page.component';

const topicRoutes: Routes = [
  {
    path: 'list',
    component: ManageTopicComponent,
    children: [
      {
        path: ':typ',
        component: TopicListComponent,
      }
    ]
  }, {
    path: 'create',
    component: CreateTopicComponent
  }, {
    path: 'review',
    component: ReviewTopicPageComponent,
    children: [{
        path: ':typ',
        component: ReviewTopicComponent
      }
    ]
  }
];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(topicRoutes),
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})

export class TopicRoutingModule { }
