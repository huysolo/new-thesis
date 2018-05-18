import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicRoutingModule } from './topic-routing.module';
import { CreateTopicComponent } from './components/create-topic/create-topic.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../core/material.module';
import { TopidDetailComponent } from './components/topid-detail/topid-detail.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {NgxPaginationModule} from 'ngx-pagination';
import { ReviewTopicComponent } from './pages/review-topic/review-topic.component'; // <-- import the module
import {MatTableModule} from '@angular/material/table';
import { CdkTableModule } from '@angular/cdk/table';
import { ReviewTopicPageComponent } from './pages/review-topic-page/review-topic-page.component';
import { ResultTopicComponent } from './pages/result-topic/result-topic.component';
@NgModule({
  imports: [
    CommonModule,
    TopicRoutingModule,
    FormsModule,
    MaterialModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    MatTableModule,
    CdkTableModule
  ],
  declarations: [
    TopicContentComponent,
    TopicListComponent,
    ManageTopicComponent,
    CreateTopicComponent,
    TopidDetailComponent,
    ReviewTopicComponent,
    ReviewTopicPageComponent,
    ResultTopicComponent,
  ],
  entryComponents: [
    TopidDetailComponent,
  ]
})
export class TopicModule { }
