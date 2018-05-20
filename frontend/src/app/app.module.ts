import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import {AuthService} from './core/auth.service';
import {UserModule} from './user/user.module';
import { MainPageComponent } from './main-page/main-page.component';
import { CoreModule } from './core/core.module';
import {MeetingModule} from './meeting/meeting.module';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    MainPageComponent
    
  ],
  imports: [
    CoreModule,
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    UserModule,
    MeetingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
