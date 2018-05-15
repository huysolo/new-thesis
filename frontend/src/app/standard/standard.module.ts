import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageStandardComponent } from './pages/manage-standard/manage-standard.component';
import { StandardRoutingModule } from './standard-routing.module';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import {MatTabsModule} from '@angular/material/tabs';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatTableModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxPaginationModule,
    StandardRoutingModule,
    MatGridListModule,
    MatTableModule,
    CdkTableModule
  ],
  declarations: [ManageStandardComponent]
})
export class StandardModule { }
