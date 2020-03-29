import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import {MovieModule} from '../movie/movie.module';
import { UserHistoryComponent } from './user-history/user-history.component';

@NgModule({
  declarations: [
    UserMenuComponent,
    WatchlistComponent,
    UserHistoryComponent,
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
