// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import {RouterModule} from '@angular/router';
//
// @NgModule({
//   declarations: [],
//   imports: [
//     CommonModule,
//     RouterModule
//   ]
// })
// export class AuthModule { }
//

// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import {RouterModule} from '@angular/router';
// import {LoginComponent} from './login/login.component';
// import {UserComponent} from '../user/user.component';
// import {AdminComponent} from '../admin/admin.component';
// import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
// import {RegisterComponent} from './register/register.component';
// import {SuccessfulRegistrationComponent} from './successful-registration/successful-registration.component';
// import {ResetPasswordComponent} from './reset-password/reset-password.component';
// import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
// import {TokenInterceptor} from './TokenInterceptor';
// import {AuthService} from './auth-service';
// import {BrowserModule} from '@angular/platform-browser';
// import {AppRoutingModule} from '../app-routing.module';
// import {ReactiveFormsModule} from '@angular/forms';
//
// @NgModule({
//   declarations: [
//     LoginComponent,
//     UserComponent,
//     AdminComponent,
//     ForgotPasswordComponent,
//     RegisterComponent,
//     SuccessfulRegistrationComponent,
//     ResetPasswordComponent,
//   ],
//   imports: [
//     CommonModule,
//     RouterModule,
//     ReactiveFormsModule,
//     HttpClientModule,
//   ],
//   providers: [[
//     {
//       provide: HTTP_INTERCEPTORS,
//       useClass: TokenInterceptor,
//       multi: true
//     }
//   ], [AuthService]]
// })
// export class AuthModule { }
