import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './auth/service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { UsercComponent } from './userc/userc.component';
import { AdmincComponent } from './adminc/adminc.component';
import {TokenInterceptor} from './auth/TokenInterceptor';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {RegisterComponent} from './auth/register/register.component';
import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsercComponent,
    AdmincComponent,
    ForgotPasswordComponent,
    RegisterComponent,
    SuccessfulRegistrationComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [[
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }
], [AuthService]],
  bootstrap: [AppComponent]
})
export class AppModule { }
