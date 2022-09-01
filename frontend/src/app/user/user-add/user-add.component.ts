import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

import { RoleService } from 'src/app/role.service';
import { User } from "../../user";
import { Role } from 'src/app/role';

@Component({
  selector: 'app-user-add',
  templateUrl: '../user-edit/user-edit.component.html',
  styleUrls: ['../user-edit/user-edit.component.css']
})
export class UserAddComponent implements OnInit {
  title = "Add new user"
  user : User = {
    id: 0,
    name: '',
    firstName: '',
    lastName: '',
    role: 'USER',
    status: 'ACTIVE',
    createdAt: new Date('2000-01-01T00:00:00')
  };
  roles: Role[] = [];
  
  constructor(
    private location: Location,
    private roleService: RoleService,) { }

  ngOnInit(): void {
    this.getRoles();
  }

  getRoles(): void {
    this.roleService.getRoles()
      .subscribe(r => this.roles = r);
  }

  goBack(): void {
    this.location.back();
  }

}
