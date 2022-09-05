import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers()
      .subscribe(u => this.users = u);
  }

  lockUser(id: number): void {
    this.userService.lockUser(id)
      .subscribe({
        next: () => this.ngOnInit(),
        error: (e) => console.error(e)
      });
  }

  unlockUser(id: number): void {
    this.userService.unlockUser(id)
      .subscribe({
        next: () => this.ngOnInit(),
        error: (e) => console.error(e)
      });
  }

}
