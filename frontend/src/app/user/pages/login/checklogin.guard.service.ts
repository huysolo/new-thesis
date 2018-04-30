import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router/src/interfaces';
import { AuthService } from '../../../core/auth.service';

@Injectable()
export class CheckloginGuard implements CanActivate  {

  constructor(private authService : AuthService) { }

  canActivate(){
    return this.authService.isLogin();
  }

}
