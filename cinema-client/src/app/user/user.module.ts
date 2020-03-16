import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {UserRoutingModule} from './user-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { ManageAccountComponent } from './my-account/manage-account.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import {MovieModule} from '../movie/movie.module';

@NgModule({
  declarations: [
    ResetPasswordComponent,
    ManageAccountComponent,
    UserMenuComponent,
    WatchlistComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    UserRoutingModule,
    MovieModule
  ]
})
export class UserModule { }
