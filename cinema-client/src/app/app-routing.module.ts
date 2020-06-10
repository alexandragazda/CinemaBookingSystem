import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {ErrorComponent} from './components/error/error.component';
import {AskBookingOrderComponent} from './components/booking-order-utils/ask-booking-order/ask-booking-order.component';

const routes: Routes = [
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
  },
  {
    path: 'booking',
    loadChildren: () => import('./booking/booking.module').then(m => m.BookingModule),
  },
  {
    path: 'order',
    loadChildren: () => import('./order/order.module').then(m => m.OrderModule),
  },
  {
    path: 'select-booking-order',
    component: AskBookingOrderComponent,
  },
  {
    path: 'error',
    component: ErrorComponent,
  },
  { path: '**', redirectTo: 'movies' } // for invalid paths
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
