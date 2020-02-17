import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import {AdminComponent} from '../admin/admin.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {RegisterComponent} from './register/register.component';
// import {SuccessfulRegistrationComponent} from './successful-registration/successful-registration.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthRoutingModule} from './auth-routing.module';
import {AuthService} from './auth-service';
import { MainAuthComponent } from './main-auth/main-auth.component';

@NgModule({
  declarations: [
      LoginComponent,
      AdminComponent,
      ForgotPasswordComponent,
      RegisterComponent,
      // SuccessfulRegistrationComponent,
      MainAuthComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
  ],
  exports: [
    LoginComponent,
    RegisterComponent
  ],
  providers: [
      [AuthService]
  ]
})
export class AuthModule { }
