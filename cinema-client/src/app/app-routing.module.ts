import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {UserComponent} from './user/user.component';
import {AdminComponent} from './admin/admin.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {RegisterComponent} from './auth/register/register.component';
import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'successful-registration',
    component: SuccessfulRegistrationComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
