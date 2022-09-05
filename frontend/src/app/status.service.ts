import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Status } from './status';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  private userApiUrl = 'http://localhost:8080/status';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>(this.userApiUrl)
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
