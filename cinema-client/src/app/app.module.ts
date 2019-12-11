import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './auth/login/service';
import {HttpClientModule} from '@angular/common/http';
import { UsercComponent } from './userc/userc.component';
import { AdmincComponent } from './adminc/adminc.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsercComponent,
    AdmincComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
