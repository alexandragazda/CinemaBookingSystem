import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TicketsComponent} from './tickets/tickets.component';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {SeatsComponent} from './seats/seats.component';
import {ConfirmationComponent} from './confirmation/confirmation.component';
import {SuccessfulBookingComponent} from './successful-booking/successful-booking.component';

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
    path: 'confirmation',
    component: ConfirmationComponent,
  },
  {
    path: 'successful-booking',
    component: SuccessfulBookingComponent,
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

export class BookingRoutingModule { }
