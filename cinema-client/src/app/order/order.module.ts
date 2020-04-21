import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {OrderRoutingModule} from './order-routing.module';
import {OrderService} from './order-service';
import { ConcessionsComponent } from './concessions/concessions.component';
import { ConcessionItemComponent } from './concessions/concession-item/concession-item.component';
import {NoRegistrationComponent} from './ask-login/no-registration/no-registration.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {AuthModule} from '../auth/auth.module';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {NgbTimepickerModule} from '@ng-bootstrap/ng-bootstrap';
import { SuccessfulOrderComponent } from './successful-order/successful-order.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import { PickUpComponent } from './pick-up/pick-up.component';
import { OrderStepComponent } from './order-step/order-step.component';

@NgModule({
  declarations: [
    ConcessionsComponent,
    ConcessionItemComponent,
    NoRegistrationComponent,
    AskLoginComponent,
    ConfirmationComponent,
    SuccessfulOrderComponent,
    PickUpComponent,
    OrderStepComponent,
  ],
  imports: [
    CommonModule,
    OrderRoutingModule,
    ReactiveFormsModule,
    AuthModule,
    NgbTimepickerModule,
    Ng4LoadingSpinnerModule.forRoot(),
  ],
  providers: [
    [OrderService]
  ],
  exports: [
    NoRegistrationComponent
  ]
})

export class OrderModule { }
