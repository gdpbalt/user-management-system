import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Role } from './role';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private userApiUrl = 'http://localhost:8080/role';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(this.userApiUrl)
      .pipe(
        tap(_ => this.log('fetched roles')),
        catchError(this.handleError<Role[]>('getRoles', []))
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
    this.messageService.add(`RoleService: ${message}`);
  }

}
