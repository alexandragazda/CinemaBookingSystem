import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {RouterModule, Routes} from '@angular/router';
import {ManageAccountComponent} from './my-account/manage-account.component';
import {WatchlistComponent} from './watchlist/watchlist.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'manage-account',
    pathMatch: 'full'
  },
  {
    path: 'manage-account',
    component: ManageAccountComponent,
  },
  {
    path: 'watchlist',
    component: WatchlistComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
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

export class UserRoutingModule { }
