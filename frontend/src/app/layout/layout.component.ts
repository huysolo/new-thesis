import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import { Router } from '@angular/router';
import { TopicService } from '../topic/topic.service';
import {TaskService} from '../task/task.service';
import { SemesterService } from '../core/semester.service';
import { ProfService } from '../core/prof.service';
import { LayoutService } from './layout.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent implements OnInit {
  topicCount: Array<any>;
  constructor(
    public taskService: TaskService,
    public authService: AuthService,
    public layoutSv: LayoutService,
    private router: Router,
    public semSv: SemesterService,
    private profSv: ProfService
  ) {
      profSv.init();
  }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

}
