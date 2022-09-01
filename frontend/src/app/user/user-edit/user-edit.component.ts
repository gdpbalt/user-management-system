import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { UserService } from '../../user.service';
import { RoleService } from 'src/app/role.service';
import { User } from "../../user";
import { Role } from 'src/app/role';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  title = "Edit user's detail";
  user?: User;
  roles: Role[] = [];
  
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private roleService: RoleService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getUser();
    this.getRoles();
  }

  getUser(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getUser(id)
      .subscribe(u => this.user = u);
  }

  getRoles(): void {
    this.roleService.getRoles()
      .subscribe(r => this.roles = r);
  }

  goBack(): void {
    this.location.back();
  }

}
