import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin!: FormGroup;
  isLoginIncorrect = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.isLoginIncorrect = false;
    const val = this.formLogin.value;
    if (val.login && val.password) {
      this.authService.login(val.login, val.password)
        .subscribe({
          next: () => {
            console.log("User is logged in");
            this.router.navigateByUrl('/');
          },
          error: (e) => {
            console.error(e);
            this.isLoginIncorrect = true;
          }
        })
    }
  }

}
