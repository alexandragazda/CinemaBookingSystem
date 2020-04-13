import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieRoutingModule} from './movie-routing.module';
import {MovieService} from './movie-service';
import {PlayingNowMovieComponent} from './playing-now/playing-now-movie/playing-now-movie.component';
import { MovieShowtimesComponent } from './movie-showtimes/movie-showtimes.component';
import { PlayingNowComponent } from './playing-now/playing-now.component';
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
      PlayingNowComponent,
      PlayingNowMovieComponent,
      MovieShowtimesComponent,
      PlayingNowComponent,
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
    PlayingNowMovieComponent,
    MovieDetailsComponent
  ],
  providers: [
      [MovieService]
  ]
})

export class MovieModule { }

