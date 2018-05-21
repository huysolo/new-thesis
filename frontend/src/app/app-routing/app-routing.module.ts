import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from '../main-page/main-page.component';
import {LoginComponent} from '../user/pages/login/login.component';
import { AuthGuardUserService } from '../core/auth-guard-user.service';
import {LayoutComponent} from '../layout/layout.component';
import { NotFoundComponent } from '../not-found/not-found.component';

const appRoutes: Routes = [
  { path: '', loadChildren: '../layout/layout.module#LayoutModule'},
  { path: '404', component: NotFoundComponent},
  { path: '**', redirectTo: '404'}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
