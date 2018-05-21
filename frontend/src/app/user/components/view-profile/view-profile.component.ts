import { Component, OnInit, Input } from '@angular/core';
import { LoginService } from '../../pages/login/login.service';
import { ManageUser } from '../../../models/ManageUser';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

  @Input() user: ManageUser;
  constructor(public userSv: LoginService) { }

  ngOnInit() {

  }

}
