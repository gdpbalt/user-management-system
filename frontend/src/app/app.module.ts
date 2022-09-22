import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { AppComponent } from './app.component';
import { MessagesComponent } from './messages/messages.component';
import { UsersComponent } from './users/users.component';
import { UserViewComponent } from './user-view/user-view.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { LoginComponent } from './login/login.component';
import { authInterceptorProviders } from './_helper/auth.interceptor';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
    imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  declarations: [
    AppComponent,
    MessagesComponent,
    UsersComponent,
    UserViewComponent,
    UserEditComponent,
    LoginComponent,
    NavbarComponent
  ],
  providers: [DatePipe, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
