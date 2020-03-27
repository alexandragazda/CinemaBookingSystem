import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResetPasswordComponent} from './my-account/reset-password/reset-password.component';
import {UserRoutingModule} from './user-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { ManageAccountComponent } from './my-account/manage-account.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import {MovieModule} from '../movie/movie.module';
// import {MyAccountModule} from './my-account/my-account.module';
import { ModifyInfoComponent } from './my-account/modify-info/modify-info.component';

@NgModule({
  declarations: [
    ResetPasswordComponent,
    ManageAccountComponent,
    UserMenuComponent,
    WatchlistComponent,
    ModifyInfoComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    UserRoutingModule,
    MovieModule,
    // MyAccountModule
  ]
})
export class UserModule { }
