import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserViewComponent } from './user-view/user-view.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserAddComponent } from './user-add/user-add.component';

@NgModule({
  declarations: [
    UserViewComponent,
    UserEditComponent,
    UserAddComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    UserViewComponent,
    UserEditComponent,
    UserAddComponent
  ]
})
export class UserModule { }
