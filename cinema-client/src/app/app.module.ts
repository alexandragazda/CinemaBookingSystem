import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from '@angular/forms';
import {GeneralMenuComponent} from './components/general-menu/general-menu.component';
import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
import {DatePipe} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptor} from './auth/TokenInterceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule} from '@angular/material';
import { ErrorComponent } from './components/error/error.component';
import {AskBookingOrderComponent} from './components/booking-order-utils/ask-booking-order/ask-booking-order.component';
import {MovieModule} from './movie/movie.module';

@NgModule({
  declarations: [
    AppComponent,
    GeneralMenuComponent,
    ErrorComponent,
    AskBookingOrderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxYoutubePlayerModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MovieModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true,
  }, [DatePipe]],
  bootstrap: [AppComponent]
})
export class AppModule { }
