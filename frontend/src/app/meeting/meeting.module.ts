import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MeetingRoutingModule } from './meeting-routing.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MeetingService } from './meeting.service';

import { MeetingCreateComponent } from './component/meeting-create/meeting-create.component';
import { MeetingDetailComponent } from './component/meeting-detail/meeting-detail.component';
import { MeetingContentComponent } from './component/meeting-content/meeting-content.component';
import { MeetingListComponent } from './component/meeting-list/meeting-list.component';
import { FilterPipe } from './component/meeting-content/filter.pipe';

import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MeetingDetailPageComponent } from './component/meeting-detail-page/meeting-detail-page.component';


@NgModule({
  imports: [
    CommonModule,
    MeetingRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatChipsModule,
    MatGridListModule,
    MatTooltipModule,
    MatTabsModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatSnackBarModule
  ],
  declarations: [
    MeetingDetailComponent,
    MeetingContentComponent,
    MeetingListComponent,
    FilterPipe,
    MeetingDetailPageComponent,
    MeetingCreateComponent
  ],
  providers: [MeetingService],
  exports: [MeetingCreateComponent]
})
export class MeetingModule { }

