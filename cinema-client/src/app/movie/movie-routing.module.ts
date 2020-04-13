import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {PlayingNowComponent} from './playing-now/playing-now.component';
import {MovieShowtimesComponent} from './movie-showtimes/movie-showtimes.component';
import {ComingSoonComponent} from './coming-soon/coming-soon.component';
import {MovieInfoComponent} from './movie-info/movie-info.component';
import {AllAvailableComponent} from './all-available/all-available.component';
import {MostPopularComponent} from './most-popular/most-popular.component';

const routes: Routes = [
  {
    path: 'all',
    component: AllAvailableComponent,
  },
  {
    path: '',
    redirectTo: 'all',
    pathMatch: 'full'
  },
  {
    path: 'now',
    component: PlayingNowComponent,
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
    path: 'most-popular',
    component: MostPopularComponent,
  },
  {
    path: ':title/:id/info',
    component: MovieInfoComponent,
  },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class MovieRoutingModule { }
