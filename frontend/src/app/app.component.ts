import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorageService } from './_service/token-storage.service';
import { AuthService } from './_service/auth.service';

const LOGIN_PAGE = '/login';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  constructor(
    private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
    } else {
      this.router.navigate([LOGIN_PAGE]);
    }
  }

  logout(): void {
    if (!!this.tokenStorageService.getToken()) {
      this.authService.logout()
        .subscribe(() => console.log("Logout successfull"));
    }
    this.tokenStorageService.signOut();
    this.router.navigate([LOGIN_PAGE]);
  }
}
