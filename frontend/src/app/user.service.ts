
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from './user';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userApiUrl = 'http://localhost:8080/user';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userApiUrl)
      .pipe(
        tap(_ => this.log('fetched users')),
        catchError(this.handleError<User[]>('getUsers', []))
      );
  }

  checkIfUserExist<Data>(name: string): Observable<boolean> {
    const url = `${this.userApiUrl}/by-name?name=${name}`;
    return this.http.get(url, { responseType: 'text', observe: 'response' })
      .pipe(
        map(data => true),
        catchError(data => of(false))
      );
  }

  getUser(id: number): Observable<User> {
    const url = `${this.userApiUrl}/${id}`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`fetched user by id=${id}`)),
      catchError(this.handleError<User>(`getUser id=${id}`))
    );
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.userApiUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => this.log(`added user w/ id=${newUser.id}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  updateUser(user: User): Observable<any> {
    const url = `${this.userApiUrl}/${user.id}`

    return this.http.put(url, user, this.httpOptions).pipe(
      tap(_ => this.log(`updated user id=${user.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }

  lockUser(id: number): Observable<User> {
    const url = `${this.userApiUrl}/${id}/lock`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`locked user by id=${id}`)),
      catchError(this.handleError<User>(`lockUser id=${id}`))
    );
  }

  unlockUser(id: number): Observable<User> {
    const url = `${this.userApiUrl}/${id}/unlock`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`unlocked user by id=${id}`)),
      catchError(this.handleError<User>(`unlockUser id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`UserService: ${message}`);
  }

}
