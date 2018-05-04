import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageStandardComponent } from './pages/manage-standard/manage-standard.component';
import { StandardRoutingModule } from './standard-routing.module';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxPaginationModule,
    StandardRoutingModule
  ],
  declarations: [ManageStandardComponent]
})
export class StandardModule { }
