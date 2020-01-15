import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {RouterModule, Routes} from '@angular/router';
import {MyAccountComponent} from './my-account/my-account.component';

const routes: Routes = [
  {
    path: '',
    component: MyAccountComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class UserRoutingModule { }
