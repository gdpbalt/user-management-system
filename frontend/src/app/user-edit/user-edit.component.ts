import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { UserService } from '../user.service';
import { RoleService } from 'src/app/role.service';
import { StatusService } from '../status.service';
import { User } from "../user";
import { Role } from 'src/app/role';
import { Status } from '../status';
import { passwordValidator } from '../shared/password-validation';
import { repeatePasswordValidator } from '../shared/repeat-password-validation';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
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
    private statusService: StatusService) { }

  ngOnInit(): void {
    if (this.location.path().toLowerCase().indexOf('edit') > -1) {
      this.isEditComponent = true;
    }
    this.createFrom();
    this.getRoles();
    this.getStatuses();
    if (this.isEditComponent) {
      this.title = "Edit user's detail";
      this.userId = Number(this.route.snapshot.paramMap.get('id'));
      this.getUser(this.userId);
    } else {
      this.title = "Add new user";
    }
  }

  getUser(id: number): void {
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

  getRoles(): void {
    this.roleService.getRoles()
      .subscribe(r => this.roles = r);
  }

  getStatuses(): void {
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

  get name() {
    return this.userForm.get('name');
  }

  get password() {
    return this.userForm.get('password');
  }

  get repeatPassword() {
    return this.userForm.get('repeatPassword');
  }

  get firstName() {
    return this.userForm.get('firstName');
  }

  get lastName() {
    return this.userForm.get('lastName');
  }

  get role() {
    return this.userForm.get('role');
  }

  get status() {
    return this.userForm.get('status');
  }

}
