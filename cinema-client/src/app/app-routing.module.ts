import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {UsercComponent} from './userc/userc.component';
import {AdmincComponent} from './adminc/adminc.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {RegisterComponent} from './auth/register/register.component';
import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'userc',
    component: UsercComponent
  },
  {
    path: 'adminc',
    component: AdmincComponent
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
