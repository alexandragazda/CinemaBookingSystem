import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieRoutingModule} from './movie-routing.module';
import {MovieService} from './movie-service';
import {NowPlayingMovieComponent} from './now-playing/now-playing-movie/now-playing-movie.component';
import { MovieShowtimesComponent } from './movie-showtimes/movie-showtimes.component';
import { NowPlayingComponent } from './now-playing/now-playing.component';
import {MovieDetailsComponent} from './movie-details/movie-details.component';
import {NgxYoutubePlayerModule} from 'ngx-youtube-player';
import {SafePipe} from './SafePipe';
import { ComingSoonComponent } from './coming-soon/coming-soon.component';
import { MovieInfoComponent } from './movie-info/movie-info.component';
import { MovieItemComponent } from './movie-item/movie-item.component';
import { AllAvailableComponent } from './all-available/all-available.component';
import {FormsModule} from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {FilterPipe} from './all-available/filter.pipe';
import { MostPopularComponent } from './most-popular/most-popular.component';

@NgModule({
  declarations: [
      NowPlayingComponent,
      NowPlayingMovieComponent,
      MovieShowtimesComponent,
      MovieDetailsComponent,
      SafePipe,
      ComingSoonComponent,
      MovieInfoComponent,
      MovieItemComponent,
      AllAvailableComponent,
      FilterPipe,
      MostPopularComponent,
  ],
  imports: [
    CommonModule,
    MovieRoutingModule,
    NgxYoutubePlayerModule,
    FormsModule,
    Ng2SearchPipeModule,
  ],
  exports: [
    NowPlayingMovieComponent,
    MovieDetailsComponent
  ],
  providers: [
      [MovieService]
  ]
})

export class MovieModule { }

