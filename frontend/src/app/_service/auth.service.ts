import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

const AUTH_API_URL = 'http://localhost:8080/';
const AUTH_LOGIN = 'login';
const AUTH_LOGOUT = 'signout';
const REFRESH_TOKEN = 'refreshtoken';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(login: string, password: string): Observable<any> {
    return this.http.post(AUTH_API_URL + AUTH_LOGIN, { login, password }, httpOptions);
  }

  refreshToken(token: string): Observable<any> {
    return this.http.post(AUTH_API_URL + REFRESH_TOKEN, {
      refreshToken: token
    }, httpOptions);
  }

  logout(): Observable<any> {
    return this.http.get(AUTH_API_URL + AUTH_LOGOUT);
  }
}
