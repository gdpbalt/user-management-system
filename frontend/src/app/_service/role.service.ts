import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Role } from '../_model/role';
import { MessageService } from './message.service';

const API_URL = "http://localhost:8080/"
const API_URL_ROLE = 'role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }


  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(API_URL + API_URL_ROLE)
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
