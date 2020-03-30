import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {WatchlistComponent} from './watchlist/watchlist.component';
import {UserHistoryComponent} from './user-history/user-history.component';

const routes: Routes = [
  {
    path: 'manage-account',
    loadChildren: () => import('./manage/manage.module').then(m => m.ManageModule),
  },
  {
    path: '',
    redirectTo: 'manage-account',
    pathMatch: 'full'
  },
  {
    path: 'watchlist',
    component: WatchlistComponent,
  },
  {
    path: 'history',
    component: UserHistoryComponent,
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
