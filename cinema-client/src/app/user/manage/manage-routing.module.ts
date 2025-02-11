import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {RouterModule, Routes} from '@angular/router';
import {ManageAccountComponent} from './manage-account/manage-account.component';
import {ModifyInfoComponent} from './modify-info/modify-info.component';

const routes: Routes = [
  {
    path: '',
    component: ManageAccountComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },
  {
    path: 'modify-info',
    component: ModifyInfoComponent,
  },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class ManageRoutingModule { }
