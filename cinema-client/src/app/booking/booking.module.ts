import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketsComponent } from './tickets/tickets.component';
import {BookingRoutingModule} from './booking-routing.module';
import { TicketItemComponent } from './tickets/ticket-item/ticket-item.component';
import {BookingService} from './booking-service';
import {MatFormFieldModule, MatInputModule, MatStepperModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    TicketsComponent,
    TicketItemComponent,
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [
    [BookingService]
  ]
})
export class BookingModule { }
