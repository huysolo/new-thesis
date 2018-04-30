import { Injectable } from '@angular/core';
import { CurrUserInfo } from '../user/pages/login/curr-user-info';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Injectable()

export class AuthService {
  constructor() {
  }
  public getUserId(): string {
    return localStorage.getItem('userID');
  }
  public getUsername(): string {
    return localStorage.getItem('username');
  }
  public getPassword(): string {
    return localStorage.getItem('password');
  }
  public getFirstname(): string {
    return localStorage.getItem('firstname');
  }
  public getLastname(): string {
    return localStorage.getItem('lastname');
  }
  public getEmail(): string {
    return localStorage.getItem('email');
  }
  public getGender(): string {
    return localStorage.getItem('gender');
  }
  public getSkills(): string {
    return localStorage.getItem('skills');
  }
  public getDegree(): string {
    return localStorage.getItem('degree');
  }
  public getProfID(): number {
    return parseInt(localStorage.getItem('profID'));
  }
  public getTopicID(): number {
    return parseInt(localStorage.getItem('topicID'));
  }
  public getToken(): string {
    return localStorage.getItem('token');
  }
  public isLogin(): boolean {
    return localStorage.getItem('isLogin') === 'true' ;
  }
  public isTeamLead(): boolean {
    return localStorage.getItem('teamLead') === '1' ;
  }
  public isStudent(): boolean {
    return localStorage.getItem('isStudent') === 'true';
  }

  public isProfessor(): boolean {
    return this.isLogin() && !this.isStudent();
  }
}
