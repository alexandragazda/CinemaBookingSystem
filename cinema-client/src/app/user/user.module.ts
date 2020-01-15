import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {UserRoutingModule} from './user-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { MyAccountComponent } from './my-account/my-account.component';

@NgModule({
  declarations: [
    ResetPasswordComponent,
    MyAccountComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
