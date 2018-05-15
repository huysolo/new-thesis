import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskContentComponent } from './components/task-content/task-content.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {TaskRoutingModule} from './task-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TaskService} from './task.service';
import {FilterPipe} from './components/task-content/filter.pipe';
import { FormUploadComponent } from './components/upload/form-upload/form-upload.component';
import { ListUploadComponent } from './components/upload/list-upload/list-upload.component';
import { UploadFileService } from './upload-file.service';
import { TaskChatgroupComponent } from './components/task-chatgroup/task-chatgroup.component';
import { TaskDetailComponent } from './components/task-detail/task-detail.component';
import { TaskCreateComponent } from './components/task-create/task-create.component';
import { TaskCommentComponent } from './components/task-comment/task-comment.component';
import { TaskPaginationComponent } from './components/task-pagination/task-pagination.component';
import { DetailsUploadComponent } from './components/upload/detail-upload/detail-upload.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { StudentTaskPipe } from './student-task.pipe';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule,
  MatButtonModule, MatButtonToggleModule, MatCheckboxModule,
  MatDatepickerModule, MatListModule, MatSelectModule, MatCardModule} from '@angular/material';
import { TaskDetailPageComponent } from './pages/task-detail-page/task-detail-page.component';
@NgModule({
  imports: [
    CommonModule,
    TaskRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatListModule,
    MatSelectModule,
    MatCardModule
  ],
  declarations: [
    TaskContentComponent,
    TaskListComponent,
    FilterPipe,
    DetailsUploadComponent,
    FormUploadComponent,
    ListUploadComponent,
    TaskChatgroupComponent,
    TaskDetailComponent,
    TaskCreateComponent,
    TaskCommentComponent,
    TaskPaginationComponent,
    StudentTaskPipe,
    TaskDetailPageComponent
  ],
  providers: [TaskService, UploadFileService],
  entryComponents: [TaskContentComponent, TaskCreateComponent]
})
export class TaskModule { }
