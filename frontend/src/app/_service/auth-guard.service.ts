import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router) { }

  canActivate(): boolean {
    if (!this.tokenStorageService.getToken()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

}
