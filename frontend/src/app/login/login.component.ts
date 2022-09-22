import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AuthService } from '../_service/auth.service';
import { TokenStorageService } from '../_service/token-storage.service';

const USER_PAGE = '/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent implements OnInit {
  formLogin!: FormGroup;
  isLoginFailed = false;
  returnUrl: string = '';

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    const login: string = this.formLogin.get('username')?.value;
    const password: string = this.formLogin.get('password')?.value;

    this.authService.login(login, password)
      .subscribe({
        next: (data) => {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveRefreshToken(data.refreshToken);
          this.tokenStorage.saveUser(data);
          window.location.href = USER_PAGE;
          return of(false);
        },

        error: (error) => {
          this.isLoginFailed = true;
        }
      });
  }
}
