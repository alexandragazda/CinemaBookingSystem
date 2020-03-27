import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './my-account/reset-password/reset-password.component';
import {RouterModule, Routes} from '@angular/router';
import {ManageAccountComponent} from './my-account/manage-account.component';
import {WatchlistComponent} from './watchlist/watchlist.component';
import {ModifyInfoComponent} from './my-account/modify-info/modify-info.component';

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
    path: 'manage-account/reset-password',
    component: ResetPasswordComponent,
  },
  {
    path: 'manage-account/modify-info',
    component: ModifyInfoComponent,
  },
  // {
  //   path: 'manage-account',
  //   loadChildren: () => import('./my-account/my-account.module').then(m => m.MyAccountModule),
  // },
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
