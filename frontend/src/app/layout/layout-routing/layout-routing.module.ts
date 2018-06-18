import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout.component';
import { MainPageComponent } from '../../main-page/main-page.component';
import { AuthGuardUserService } from '../../core/auth-guard-user.service';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        canActivate: [AuthGuardUserService],
        children: [
            { path: '', redirectTo: 'mainpage'},
            { path: 'mainpage', component: MainPageComponent , data: {breadcrumbs: 'Home' }},
            { path: 'user', loadChildren: 'app/user/user.module#UserModule' , data: {breadcrumbs: 'User'} },
            { path: 'task', loadChildren: 'app/task/task.module#TaskModule' , data: {breadcrumbs: 'Task' } },
            { path: 'topic', loadChildren: 'app/topic/topic.module#TopicModule' , data: {breadcrumbs: 'Topic'}  },
            { path: 'standard', loadChildren: 'app/standard/standard.module#StandardModule' , data: {breadcrumbs: 'Standard' } },
            { path: 'meeting', loadChildren: 'app/meeting/meeting.module#MeetingModule' , data: {breadcrumbs: 'Meeting' } },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
