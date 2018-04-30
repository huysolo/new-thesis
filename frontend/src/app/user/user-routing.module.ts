import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ManageAccountComponent } from './pages/manage-account/manage-account.component';
import {CheckloginGuard} from './pages/login/checklogin.guard.service';
import { AuthGuardUserService } from '../core/auth-guard-user.service';

const userRotes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'manager',
    component: ManageAccountComponent,
    canActivate: [AuthGuardUserService]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(userRotes)],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
