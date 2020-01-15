import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {MovieListComponent} from './components/home/movie-list/movie-list.component';

const routes: Routes = [
  {
    path: 'home',
    component: MovieListComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
  },
  {
    path: 'my-account',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
  },
  {
    path: 'movies/:title/:id',
    loadChildren: () => import('./movie/movie.module').then(m => m.MovieModule),
  }
  // de adaugat path invalid => movie-home
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
