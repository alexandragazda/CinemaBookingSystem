import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ManageRoutingModule} from './manage-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import { ManageAccountComponent} from './manage-account/manage-account.component';
import { ModifyInfoComponent} from './modify-info/modify-info.component';
import {UserModule} from '../user.module';

@NgModule({
  declarations: [
    ResetPasswordComponent,
    ManageAccountComponent,
    ModifyInfoComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    ManageRoutingModule,
    UserModule,
  ],
})

export class ManageModule { }
