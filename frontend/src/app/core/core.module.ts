import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth.service';
import { TopicService } from '../topic/topic.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { CommonService } from './common.service';
import { AuthGuardUserService } from './auth-guard-user.service';
import {TaskService} from '../task/task.service';

import { AuthTopicGuardService } from '../topic/auth-topic-guard.service';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {NgxPaginationModule} from 'ngx-pagination'; // <-- import the module
import { StandardService } from '../standard.service';
import { SemesterService } from './semester.service';
import { ProfService } from './prof.service';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    Ng2SearchPipeModule,
    NgxPaginationModule
  ],
  declarations: [],
  providers: [
    AuthService,
    TopicService,
    CommonService,
    AuthGuardUserService,
    TaskService,
    ProfService,
    SemesterService,
    StandardService,
    AuthTopicGuardService
  ],
  exports: [
    MaterialModule
  ]
})
export class CoreModule { }
