import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserViewComponent } from './user-view/user-view.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService as AuthGuard } from './_service/auth-guard.service'

const routes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'user', component: UsersComponent, canActivate: [AuthGuard] },
  { path: 'user/view/:id', component: UserViewComponent, canActivate: [AuthGuard] },
  { path: 'user/add', component: UserEditComponent, canActivate: [AuthGuard] },
  { path: 'user/edit/:id', component: UserEditComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
