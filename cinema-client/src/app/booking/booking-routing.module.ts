import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TicketsComponent} from './tickets/tickets.component';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {SeatsComponent} from './seats/seats.component';
import {CheckoutComponent} from './checkout/checkout.component';

const routes: Routes = [
  {
    path: 'account',
    component: AskLoginComponent
  },
  {
    path: 'tickets',
    component: TicketsComponent,
  },
  {
    path: 'seats',
    component: SeatsComponent,
  },
  {
    path: 'checkout',
    component: CheckoutComponent,
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

export class BookingRoutingModule { }
