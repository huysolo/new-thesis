import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';
import { SemesterService } from './semester.service';

@Injectable()
export class AuthGuardUserService implements CanActivate {

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    if (!this.authoSv.isLogin()) {
      this.router.navigate(['/user/login']);
    }
    return this.authoSv.isLogin();
  }
  constructor(private authoSv: AuthService, private router: Router, semSv: SemesterService) { 
    semSv.init();
  }

}
