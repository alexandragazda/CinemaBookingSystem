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
import { MovieListComponent} from './components/home/movie-list/movie-list.component';
import {MovieItemComponent} from './components/home/movie-list/movie-item/movie-item.component';

@NgModule({
  declarations: [
    AppComponent,
    GeneralMenuComponent,
    MovieItemComponent,
    MovieListComponent,
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
