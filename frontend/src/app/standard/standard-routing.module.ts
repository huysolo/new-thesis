import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { ManageStandardComponent } from './pages/manage-standard/manage-standard.component';

const standardRoutes: Routes = [{
  path: 'list',
  component: ManageStandardComponent
}];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(standardRoutes)

  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class StandardRoutingModule { }
