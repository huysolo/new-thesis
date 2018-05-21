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


@NgModule({
  imports: [
    LayoutRoutingModule,
    MaterialModule,
    CommonModule,
    McBreadcrumbsModule.forRoot()
  ],
  declarations: [
    HeaderComponent,
    LayoutComponent,
    SidebarComponent,
     MainPageComponent
  ],
  providers: [
    LayoutService
  ]
})
export class LayoutModule { }
