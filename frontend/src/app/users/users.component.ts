import { Component, OnInit } from '@angular/core';

import { User } from '../_model/user';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: []
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
      })
  }

  unlockUser(id: number): void {
    this.userService.unlockUser(id)
      .subscribe({
        next: () => this.ngOnInit(),
        error: (e) => console.error(e)
      });
  }

}
