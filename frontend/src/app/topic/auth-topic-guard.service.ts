import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../core/auth.service';

@Injectable()
export class AuthTopicGuardService implements CanActivate {

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    if (this.authoSv.isProfessor()) {
      return true;
    } else {
      return new Observable<boolean>(obs => {
        this.route.params.subscribe(param => {
          console.log(param['typ'] );
          if (param['typ'] === 'draft') {
            this.router.navigate(['/topic']);
            obs.next(false);
          }
          obs.next(true);
        });
      });
    }
  }
  constructor(private authoSv: AuthService, private route: ActivatedRoute, private router: Router) { }

}
