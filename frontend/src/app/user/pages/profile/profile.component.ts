import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { ManageUser } from '../../../models/ManageUser';
import { LayoutService } from '../../../layout/layout.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: Observable<ManageUser>;

  constructor(public userSv: LoginService, private layoutSv: LayoutService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.user = this.userSv.loadProfileById(params['id']).map(data => {
        this.layoutSv.labelName = 'Profile user: ' + data.user.userName;
        return data;
      });
    });
  }

}
