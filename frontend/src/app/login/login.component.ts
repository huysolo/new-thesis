import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../user/pages/login/login.service';
import { AuthService } from '../core/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  alert: Boolean;
  isLoading: Boolean;
  isFail: Boolean;
  ngOnInit(): void {
  }
  constructor(private loginService: LoginService, private router: Router, public authService: AuthService){

  }

  loginReq(form) {
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
