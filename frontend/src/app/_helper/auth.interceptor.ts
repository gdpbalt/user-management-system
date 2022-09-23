import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';
import { Router } from '@angular/router';

import { AuthService } from '../_service/auth.service'
import { TokenStorageService } from '../_service/token-storage.service';

const LOGIN_PAGE = '/login';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(
    private tokenService: TokenStorageService,
    private authService: AuthService,
    private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
    let authReq = req;
    const token = this.tokenService.getToken();
    if (token != null) {
      authReq = this.addTokenHeader(req, token);
    }

    return next.handle(authReq)
      .pipe(
        catchError(error => {
          if (error instanceof HttpErrorResponse
            && !authReq.url.includes(LOGIN_PAGE)
            && error.status === 401) {
            console.error("Access token error, needs to user refresh token");
            this.tokenService.clearAccessToken();
            return this.handle401Error(authReq, next);
          }
          return throwError(() => error);
        }));
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);
      const token = this.tokenService.getRefreshToken();

      if (token) {
        return this.authService.refreshToken(token)
          .pipe(
            switchMap((token: any) => {
              this.isRefreshing = false;
              this.tokenService.saveToken(token.accessToken);
              this.refreshTokenSubject.next(token.accessToken);

              return next.handle(this.addTokenHeader(request, token.accessToken));
            }),

            catchError((error) => {
              console.error("Refresh token error");
              this.isRefreshing = false;
              this.tokenService.signOut();
              this.router.navigate([LOGIN_PAGE]);
              return throwError(() => error);
            })
          );
      } else {
        this.router.navigate([LOGIN_PAGE]);
      }
    }

    return this.refreshTokenSubject.pipe(
      filter(token => token !== null),
      take(1),
      switchMap((token) => next.handle(this.addTokenHeader(request, token)))
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    return request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
