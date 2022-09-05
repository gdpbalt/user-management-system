import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserViewComponent } from './user-view/user-view.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'user', component: UsersComponent },
  { path: 'user/view/:id', component: UserViewComponent },
  { path: 'user/add', component: UserEditComponent },
  { path: 'user/edit/:id', component: UserEditComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
