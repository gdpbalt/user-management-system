import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from "@angular/router";

import { UserService } from '../_service/user.service';
import { RoleService } from '../_service/role.service';
import { StatusService } from '../_service/status.service';
import { User } from "../_model/user";
import { Role } from '../_model/role';
import { Status } from '../_model/status';
import { passwordValidator } from '../_helper/password-validation';
import { repeatePasswordValidator } from '../_helper/repeat-password-validation';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: []
})
export class UserEditComponent implements OnInit {

  isEditComponent: boolean = false;
  title: string = '';
  userId?: number;
  userForm!: FormGroup;
  roles: Role[] = [];
  statuses: Status[] = [];
  isUserExist: boolean = false;

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private roleService: RoleService,
    private statusService: StatusService,
    private router: Router) { }

  ngOnInit(): void {
    if (this.router.url.includes('edit')) {
      this.isEditComponent = true;
    }
    this.createFrom();
    this.setRolesFields();
    this.setStatusesFields();
    if (this.isEditComponent) {
      this.title = "Edit user's detail";
      this.userId = Number(this.route.snapshot.paramMap.get('id'));
      this.setUserFields(this.userId);
    } else {
      this.title = "Add new user";
    }
  }

  setUserFields(id: number): void {
    this.userService.getUser(id)
      .subscribe(user => {
        this.userForm.patchValue({
          name: user.name,
          firstName: user.firstName,
          lastName: user.lastName,
          role: user.role,
          status: user.status,
        });
      });
  }

  setRolesFields(): void {
    this.roleService.getRoles()
      .subscribe(r => this.roles = r);
  }

  setStatusesFields(): void {
    this.statusService.getStatuses()
      .subscribe(s => this.statuses = s);
  }

  goBack(): void {
    this.location.back();
  }

  createFrom(): void {
    this.userForm = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required, Validators.minLength(3), Validators.maxLength(16), Validators.pattern('[a-zA-Z]+')
        ]
      ],
      password: [
        '',
        [
          Validators.required, Validators.minLength(3), Validators.maxLength(16), Validators.pattern('[a-zA-Z0-9]+'),
          passwordValidator()
        ]
      ],
      repeatPassword: [
        '',
        [Validators.required]
      ],
      firstName: [
        '',
        [Validators.required, Validators.minLength(1), Validators.maxLength(16), Validators.pattern('[a-zA-Z]+')]
      ],
      lastName: [
        '',
        [Validators.required, Validators.minLength(1), Validators.maxLength(16), Validators.pattern('[a-zA-Z]+')]
      ],
      role: [
        'USER',
        [Validators.required]
      ],
      status: [
        'INACTIVE',
        [Validators.required]
      ],
    }, { validators: repeatePasswordValidator });
  }

  submitForm(): void {
    if (this.userForm.invalid) {
      this.userForm.markAllAsTouched();
      return;
    }
    if (this.isEditComponent) {
      this.saveData();
    } else {
      this.userService.checkIfUserExist(this.name?.value)
        .subscribe(result => {
          if (result) {
            return;
          } else {
            this.saveData();
          }
        });
    }
  }

  saveData(): void {
    const userChange: User = {
      id: Number(this.userId),
      name: this.name?.value,
      password: this.password?.value,
      firstName: this.firstName?.value,
      lastName: this.lastName?.value,
      role: this.role?.value,
      status: this.status?.value,
      createdAt: new Date()
    }
    this.userService.updateUser(userChange)
      .subscribe(() => this.goBack());
  }

  get name(): any {
    return this.userForm.get('name');
  }

  get password(): any {
    return this.userForm.get('password');
  }

  get repeatPassword(): any {
    return this.userForm.get('repeatPassword');
  }

  get firstName(): any {
    return this.userForm.get('firstName');
  }

  get lastName(): any {
    return this.userForm.get('lastName');
  }

  get role(): any {
    return this.userForm.get('role');
  }

  get status(): any {
    return this.userForm.get('status');
  }

}
