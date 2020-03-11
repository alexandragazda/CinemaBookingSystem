import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TicketsComponent} from './tickets/tickets.component';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {SeatsComponent} from './seats/seats.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {SuccessfulBookingComponent} from './successful-booking/successful-booking.component';
// import {AskBookingOrderComponent} from './ask-booking-order/ask-booking-order.component';

const routes: Routes = [
  {
    path: 'tickets',
    component: TicketsComponent,
  },
  {
    path: 'seats',
    component: SeatsComponent,
  },
  {
    path: 'account',
    component: AskLoginComponent
  },
  {
    path: 'checkout',
    component: CheckoutComponent,
  },
  {
    path: 'successful-booking',
    component: SuccessfulBookingComponent,
  },
  // {
  //   path: 'booking-order',
  //   component: AskBookingOrderComponent,
  // }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class BookingRoutingModule { }
