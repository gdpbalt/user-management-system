import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { UserService } from '../user.service';
import { USERS } from '../mock-users'

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users : User[] = [];
  
  constructor(private userService: UserService) { }
  
  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers()
      .subscribe(u => this.users = u);
  }

}
