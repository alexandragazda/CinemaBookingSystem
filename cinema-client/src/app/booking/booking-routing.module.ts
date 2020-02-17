import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TicketsComponent} from './tickets/tickets.component';
import {AskLoginComponent} from './ask-login/ask-login.component';

const routes: Routes = [
  {
    path: 'tickets',
    component: TicketsComponent,
  },
  {
    path: 'account',
    component: AskLoginComponent
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
