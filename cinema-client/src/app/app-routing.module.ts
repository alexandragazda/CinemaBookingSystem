import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
// import {HomeComponent} from './components/home/home.component';

const routes: Routes = [
  // {
  //   path: '',
  //   component: HomeComponent
  // },
  {
    path: '',
    redirectTo: 'movies',
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
    path: 'movies',
    loadChildren: () => import('./movie/movie.module').then(m => m.MovieModule),
  }
  // de adaugat path invalid => movie-showtimes
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
