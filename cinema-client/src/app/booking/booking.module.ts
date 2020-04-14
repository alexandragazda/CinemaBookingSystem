import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketsComponent } from './tickets/tickets.component';
import {BookingRoutingModule} from './booking-routing.module';
import { TicketItemComponent } from './tickets/ticket-item/ticket-item.component';
import {BookingService} from './booking-service';
import {MatFormFieldModule, MatInputModule, MatStepperModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
import { AskLoginComponent } from './ask-login/ask-login.component';
import {AuthModule} from '../auth/auth.module';
import { SeatsComponent } from './seats/seats.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { NoRegistrationComponent } from './ask-login/no-registration/no-registration.component';
import { SuccessfulBookingComponent } from './successful-booking/successful-booking.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';

@NgModule({
  declarations: [
    TicketsComponent,
    TicketItemComponent,
    AskLoginComponent,
    SeatsComponent,
    CheckoutComponent,
    NoRegistrationComponent,
    SuccessfulBookingComponent,
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    AuthModule,
    Ng4LoadingSpinnerModule.forRoot(),
  ],
  providers: [
    [BookingService]
  ]
})
export class BookingModule { }
