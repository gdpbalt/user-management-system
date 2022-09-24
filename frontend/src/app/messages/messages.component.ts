import { Component } from '@angular/core';
import { MessageService } from '../_service/message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: []
})
export class MessagesComponent {

  constructor(public messageService: MessageService) { }

}
