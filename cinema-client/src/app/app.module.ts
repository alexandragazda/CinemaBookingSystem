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
import { HomeComponent} from './components/home/home.component';
import {MovieItemComponent} from './components/home/movie-item/movie-item.component';

@NgModule({
  declarations: [
    AppComponent,
    GeneralMenuComponent,
    MovieItemComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    // NgxYoutubePlayerModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }, [DatePipe]],
  bootstrap: [AppComponent]
})
export class AppModule { }
