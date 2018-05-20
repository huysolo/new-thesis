import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicRoutingModule } from './topic-routing.module';
import { CreateTopicComponent } from './components/create-topic/create-topic.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../core/material.module';
import { TopidDetailComponent } from './components/topid-detail/topid-detail.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {NgxPaginationModule} from 'ngx-pagination';
import { ReviewTopicComponent } from './pages/review-topic/review-topic.component'; // <-- import the module
import {MatTableModule} from '@angular/material/table';
import { CdkTableModule } from '@angular/cdk/table';
import { ReviewTopicPageComponent } from './pages/review-topic-page/review-topic-page.component';
import { ResultTopicComponent } from './pages/result-topic/result-topic.component';
import { ScoreTableComponent } from './components/score-table/score-table.component';
import { TopicTableComponent } from './components/topic-table/topic-table.component';
import { ProfPipe } from '../shared/prof.pipe';
import { ReviewPipe } from './review.pipe';
import { DetailResultComponent } from './pages/detail-result/detail-result.component';
import { TopicFormComponent } from './components/topic-form/topic-form.component';
import { MatSelectModule, MatFormFieldModule } from '@angular/material';
import { CreateFormModalComponent } from './components/create-form-modal/create-form-modal.component';
import { TopicFormButtonComponent } from './components/topic-form-button/topic-form-button.component';
import { TopicFormButtonDirective } from './directives/topic-form-button.directive';
import { TopicDetailDirective } from './components/topic-detail.directive';
@NgModule({
  imports: [
    CommonModule,
    TopicRoutingModule,
    FormsModule,
    MaterialModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    MatTableModule,
    CdkTableModule,
    MatSelectModule,
    ReactiveFormsModule
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
    ScoreTableComponent,
    TopicTableComponent,
    ProfPipe,
    ReviewPipe,
    DetailResultComponent,
    TopicFormComponent,
    CreateFormModalComponent,
    TopicFormButtonComponent,
    TopicFormButtonDirective,
    TopicDetailDirective,
  ],
  entryComponents: [
    TopidDetailComponent,
    CreateFormModalComponent
  ]
})
export class TopicModule { }
