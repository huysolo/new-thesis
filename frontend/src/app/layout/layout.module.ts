import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutRoutingModule } from './layout-routing/layout-routing.module';
import { HeaderComponent } from './components/header/header.component';
import { LayoutComponent } from './layout.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MainPageComponent } from '../main-page/main-page.component';
import { CoreModule } from '../core/core.module';
import { MaterialModule } from '../core/material.module';
import { McBreadcrumbsModule } from 'ngx-breadcrumbs';
import { LayoutService } from './layout.service';
import {MeetingModule} from '../meeting/meeting.module';
import {TaskModule} from '../task/task.module';
import { SemesterComponent } from '../semester/semester.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    LayoutRoutingModule,
    MaterialModule,
    FormsModule,
    CommonModule,
    McBreadcrumbsModule.forRoot(),
    MeetingModule,
    TaskModule,
  ],
  declarations: [
    HeaderComponent,
    LayoutComponent,
    SidebarComponent,
    MainPageComponent,
    SemesterComponent
  ],
  providers:
  [LayoutService]
})
export class LayoutModule { }
