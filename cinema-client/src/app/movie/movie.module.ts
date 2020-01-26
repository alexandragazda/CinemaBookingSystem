import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieRoutingModule} from './movie-routing.module';
import {MovieService} from './movie-service';
import {MovieItemComponent} from './movies/movie-item/movie-item.component';
import { MovieShowtimesComponent } from './movie-showtimes/movie-showtimes.component';
import { MoviesComponent } from './movies/movies.component';
import {MovieDetailsComponent} from './movie-showtimes/movie-details/movie-details.component';
import {NgxYoutubePlayerModule} from 'ngx-youtube-player';

@NgModule({
  declarations: [
    MoviesComponent,
    MovieItemComponent,
    MovieShowtimesComponent,
    MoviesComponent,
    MovieDetailsComponent,
  ],
  imports: [
    CommonModule,
    MovieRoutingModule,
    NgxYoutubePlayerModule,
  ],
  providers: [
    [MovieService]
  ]
})

export class MovieModule { }

