import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Logininfo } from './logininfo';
import { HttpClient } from '@angular/common/http';
import { error } from 'selenium-webdriver';
import { LoginService } from './login.service';
import {Router} from '@angular/router';
import {AuthService} from '../../../core/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent implements OnInit {
  alert: Boolean;
  isLoading: Boolean;
  isFail: Boolean;
  constructor(private loginService: LoginService, private router: Router, private authService: AuthService) {
    this.alert = false;
  }
  ngOnInit() {
  }

  LoginReq(form) {
    this.isLoading = true;
    this.isFail = false;
    this.loginService.login(form.value).subscribe(
      data => {
        this.isLoading = false;
        if (this.authService.isLogin() === true) {
        this.router.navigate(['']);
        } else {
          this.isFail = true;
        }
      },
      err => {

      }
    );
    this.alert = true;
  }

}
