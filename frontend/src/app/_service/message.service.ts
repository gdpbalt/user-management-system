import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  messages: string[] = [];
  messageLimit = 5;

  constructor(public datepipe: DatePipe) { }

  add(message: string) {
    let currentTime = this.datepipe.transform((new Date), 'hh:mm:ss');
    this.messages.push(currentTime + ': ' + message);
    if (this.messages.length > this.messageLimit) {
      this.messages.shift();
    }
  }

  clear() {
    this.messages = [];
  }

}
