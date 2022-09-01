import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserViewComponent } from './user/user-view/user-view.component';
import { UserEditComponent } from './user/user-edit/user-edit.component';
import { UsersComponent } from './users/users.component';
import { UserAddComponent } from './user/user-add/user-add.component';

const routes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'user', component: UsersComponent },
  { path: 'user/add', component: UserAddComponent },
  { path: 'user/view/:id', component: UserViewComponent },
  { path: 'user/edit/:id', component: UserEditComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
