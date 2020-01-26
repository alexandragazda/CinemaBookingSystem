import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {MovieShowtimesComponent} from './movie-showtimes/movie-showtimes.component';

const routes: Routes = [
  {
    path: '',
    component: MoviesComponent,
  },
  {
    path: ':title/:id',
    component: MovieShowtimesComponent
  }
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
