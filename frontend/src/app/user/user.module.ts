import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { ManageAccountComponent } from './pages/manage-account/manage-account.component';
import { UserRoutingModule } from './user-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpModule} from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddheaderService } from './pages/login/addheader.service';
import {LoginService} from './pages/login/login.service';
import { AuthService } from '../core/auth.service';
import {CheckloginGuard} from './pages/login/checklogin.guard.service';


@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AddheaderService,
    multi: true
  }, LoginService, CheckloginGuard]
  ,
  declarations: [
    LoginComponent,
    ManageAccountComponent,
    LoginComponent,
    ManageAccountComponent

  ]
})
export class UserModule { }
