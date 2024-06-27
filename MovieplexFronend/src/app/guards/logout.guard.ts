import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, RouterStateSnapshot, UrlTree } from '@angular/router';
import { LoginService } from '../service/login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class LogoutGuard implements CanActivate {

  constructor(private login: LoginService) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.login.guard) { this.login.guard = false; return window.confirm('Are you sure you want to Logout?') } else { return true; }
  }
};
