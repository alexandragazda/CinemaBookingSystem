import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HomeComponent} from './home/home.component';
import {GeneralMenuComponent} from './general-menu/general-menu.component';
import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { MovieItemComponent } from './movie/movie-list/movie-item/movie-item.component';
import {DatePipe} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptor} from './auth/TokenInterceptor';

@NgModule({
  declarations: [
    AppComponent,
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
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }, [DatePipe]],
  bootstrap: [AppComponent]
})
export class AppModule { }
