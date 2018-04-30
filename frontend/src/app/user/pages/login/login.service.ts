import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrUserInfo } from './curr-user-info';
import { AuthService } from '../../../core/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
@Injectable()
export class LoginService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  login(form) {
    const loginUrl = `http://localhost:8080/login`;
    return this.httpClient.post<any>(loginUrl, {
      username: form.username,
      password: form.password
    })
      .map(res => {
        if (res) {
          console.log(res);
          localStorage.setItem('isLogin', 'true');
          localStorage.setItem('token', res.token);
          localStorage.setItem('username', res.username);
          localStorage.setItem('password', res.password);
          localStorage.setItem('isStudent', res.isStudent ? 'true' : 'false');
          localStorage.setItem('firstname', res.fistname);
          localStorage.setItem('lastname', res.lastname);
          localStorage.setItem('email', res.email);
          localStorage.setItem('gender', (res.gender === 1) ? 'male' : 'female');
          localStorage.setItem('photo', res.photo);
          localStorage.setItem('degree', res.degree);
          localStorage.setItem('skills', res.skills);
          localStorage.setItem('profID', res.profID);
          localStorage.setItem('userID', res.userID);
          localStorage.setItem('teamLead', res.teadLead);
          localStorage.setItem('topicID', res.topicID);
        } else {
          localStorage.setItem('isLogin', 'false');
        }
      });
  }

  prifile(form) {
    const loginUrl = `http://localhost:8080/profile`;
    return this.httpClient.post<any>(loginUrl, {
      username: form.username,
      password: form.password,
      firstname: form.firstname,
      lastname: form.lastname,
      email: form.email,
      gender: form.gender,
      photo: form.photo,
      degree: form.degree,
      skills: form.skills,
      profID: form.profID,
      userID: localStorage.getItem('userID')
    });
  }

}
