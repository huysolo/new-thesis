import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuardStudentService implements CanActivate {

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    if (!this.authoSv.isStudent()) {
      this.router.navigate(['']);
    }
    return this.authoSv.isStudent();
  }
  constructor(private authoSv: AuthService, private router: Router) { }

}
