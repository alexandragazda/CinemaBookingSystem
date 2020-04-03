import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import {MovieModule} from '../movie/movie.module';
import { UserHistoryComponent } from './user-history/user-history.component';
import { ValidBookingsAndOrdersComponent } from './valid-bookings-and-orders/valid-bookings-and-orders.component';

@NgModule({
  declarations: [
    UserMenuComponent,
    WatchlistComponent,
    UserHistoryComponent,
    ValidBookingsAndOrdersComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MovieModule,
  ],
  exports: [
    UserMenuComponent
  ],
})

export class UserModule { }
