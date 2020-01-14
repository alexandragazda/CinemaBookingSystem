import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import {UserComponent} from '../user/user.component';
import {AdminComponent} from '../admin/admin.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {RegisterComponent} from './register/register.component';
import {SuccessfulRegistrationComponent} from './successful-registration/successful-registration.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthRoutingModule} from './auth-routing.module';
import {AuthService} from './auth-service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from './TokenInterceptor';

@NgModule({
  declarations: [
    LoginComponent,
    UserComponent,
    AdminComponent,
    ForgotPasswordComponent,
    RegisterComponent,
    SuccessfulRegistrationComponent,
    ResetPasswordComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule
  ],
  providers: [
  //   {
  //   provide: HTTP_INTERCEPTORS,
  //   useClass: TokenInterceptor,
  //   multi: true
  // },
    [AuthService]]
})
export class AuthModule { }
