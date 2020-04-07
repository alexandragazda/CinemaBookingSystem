import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieRoutingModule} from './movie-routing.module';
import {MovieService} from './movie-service';
import {MovieItemComponent} from './movies/movie-item/movie-item.component';
import { MovieShowtimesComponent } from './movie-showtimes/movie-showtimes.component';
import { MoviesComponent } from './movies/movies.component';
import {MovieDetailsComponent} from './movie-details/movie-details.component';
import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
import {SafePipe} from './SafePipe';
import { ComingSoonComponent } from './coming-soon/coming-soon.component';
import { MovieInfoComponent } from './movie-info/movie-info.component';
import { ComingSoonMovieComponent } from './coming-soon/coming-soon-movie/coming-soon-movie.component';

@NgModule({
    declarations: [
        MoviesComponent,
        MovieItemComponent,
        MovieShowtimesComponent,
        MoviesComponent,
        MovieDetailsComponent,
        SafePipe,
        ComingSoonComponent,
        MovieInfoComponent,
        ComingSoonMovieComponent,
    ],
    imports: [
        CommonModule,
        MovieRoutingModule,
        NgxYoutubePlayerModule,
    ],
  exports: [
    MovieItemComponent,
    MovieDetailsComponent
  ],
    providers: [
        [MovieService]
    ]
})

export class MovieModule { }

