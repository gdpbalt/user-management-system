import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Status } from '../_model/status';
import { MessageService } from './message.service';

const API_URL = "http://localhost:8080/"
const API_URL_STATUS = "status"

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  getStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>(API_URL + API_URL_STATUS)
      .pipe(
        tap(_ => this.log('fetched statuses')),
        catchError(this.handleError<Status[]>('getStatuses', []))
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
    this.messageService.add(`StatusService: ${message}`);
  }
}
