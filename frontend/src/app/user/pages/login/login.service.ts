import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { CurrUserInfo } from './curr-user-info';
import { AuthService } from '../../../core/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { ManageUser } from '../../../models/ManageUser';

@Injectable()
export class LoginService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  loadProfile() {
    return this.httpClient.get<ManageUser>('http://localhost:8080/user/profile');
  }

  loadProfileById(id) {
    return this.httpClient.get<ManageUser>('http://localhost:8080/user/profile?id=' + id);
  }
  postProfile(user: ManageUser) {
    return this.httpClient.post<any>('http://localhost:8080/user/profile', user);
  }

  login(form) {
    const loginUrl = `http://localhost:8080/login`;
    return this.httpClient.post<any>(loginUrl, {
      username: form.username,
      password: form.password
    })
      .map(res => {
        if (res) {
          localStorage.setItem('isLogin', 'true');
          localStorage.setItem('token', res.token);
          localStorage.setItem('isStudent', res.isStudent ? 'true' : 'false');
          localStorage.setItem('photo', res.photo);
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
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', 'http://localhost:8080/user/avatar', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.httpClient.request(req);
  }

  getAvatar(userId) {
    return this.httpClient.get<String>('http://localhost:8080/user/avatar?id=' + userId);
  }

}
