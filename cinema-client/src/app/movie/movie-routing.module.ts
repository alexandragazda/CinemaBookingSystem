import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {MovieShowtimesComponent} from './movie-showtimes/movie-showtimes.component';
import {ComingSoonComponent} from './coming-soon/coming-soon.component';
import {MovieInfoComponent} from './movie-info/movie-info.component';

const routes: Routes = [
  {
    path: '',
    component: MoviesComponent,
  },
  {
    path: ':title/:id',
    component: MovieShowtimesComponent
  },
  {
    path: 'coming-soon',
    component: ComingSoonComponent,
  },
  {
    path: ':title/:id/info',
    component: MovieInfoComponent,
  },
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class MovieRoutingModule { }
