import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { User } from "../../user";

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
    roleIds: [1],
    status: 'ACTIVE',
    createdAt: new Date('2000-01-01T00:00:00')
  };

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

}
