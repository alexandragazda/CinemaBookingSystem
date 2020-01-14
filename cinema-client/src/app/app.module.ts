import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './auth/auth-service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { UserComponent } from './user/user.component';
import {AdminComponent} from './admin/admin.component';
import {TokenInterceptor} from './auth/TokenInterceptor';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {RegisterComponent} from './auth/register/register.component';
import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';
import {HomeComponent} from './home/home.component';
import {GeneralMenuComponent} from './general-menu/general-menu.component';

import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { MovieItemComponent } from './movie/movie-list/movie-item/movie-item.component';
import {DatePipe} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AdminComponent,
    ForgotPasswordComponent,
    RegisterComponent,
    SuccessfulRegistrationComponent,
    ResetPasswordComponent,
    HomeComponent,
    GeneralMenuComponent,
    MovieListComponent,
    MovieItemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    // NgxYoutubePlayerModule,
  ],
  providers: [[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ], [AuthService, DatePipe]],
  bootstrap: [AppComponent]
})
export class AppModule { }



// import { BrowserModule } from '@angular/platform-browser';
// import { NgModule } from '@angular/core';
//
// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { LoginComponent } from './auth/login/login.component';
// import {ReactiveFormsModule} from '@angular/forms';
// import {AuthService} from './auth/auth-service';
// import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
// import { UserComponent } from './user/user.component';
// import {AdminComponent} from './admin/admin.component';
// import {TokenInterceptor} from './auth/TokenInterceptor';
// import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
// import {RegisterComponent} from './auth/register/register.component';
// import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
// import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';
// import { HomeComponent } from './home/home.component';
// import { GeneralMenuComponent } from './general-menu/general-menu.component';
// import {AuthModule} from './auth/auth.module';
//
// // import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
//
// @NgModule({
//   declarations: [
//     AppComponent,
//     // LoginComponent,
//     // UserComponent,
//     // AdminComponent,
//     // ForgotPasswordComponent,
//     // RegisterComponent,
//     // SuccessfulRegistrationComponent,
//     // ResetPasswordComponent,
//     HomeComponent,
//     GeneralMenuComponent,
//   ],
//   imports: [
//     AuthModule,
//     BrowserModule,
//     AppRoutingModule,
//     ReactiveFormsModule,
//     HttpClientModule,
//
//     // NgxYoutubePlayerModule,
//   ],
//   providers: [[
//     {
//       provide: HTTP_INTERCEPTORS,
//       useClass: TokenInterceptor,
//       multi: true
//     }
//   ], [AuthService]],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }
//
