import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import {AdminComponent} from '../admin/admin.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {RegisterComponent} from './register/register.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
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
      MainAuthComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    Ng4LoadingSpinnerModule.forRoot(),
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
