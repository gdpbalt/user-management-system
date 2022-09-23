import { Component, OnInit } from '@angular/core';
import { Observable, of as observableOf } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorageService } from '../_service/token-storage.service';
import { AuthService } from '../_service/auth.service';

const LOGIN_PAGE = '/login';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: []
})
export class NavbarComponent implements OnInit {

  isLoggedIn$: Observable<boolean> = observableOf(false);

  constructor(
    private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn$ = observableOf(!!this.tokenStorageService.getToken());
  }

  logout(): void {
    if (!!this.tokenStorageService.getToken()) {
      this.authService.logout()
        .subscribe(() => console.log("Logout successfull"));
    }
    this.tokenStorageService.signOut();
    this.router.navigate([LOGIN_PAGE]);
    this.isLoggedIn$ = observableOf(false)
  }
}
