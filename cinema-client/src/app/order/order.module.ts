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
import { CheckoutComponent } from './checkout/checkout.component';
import {NgbTimepickerModule} from '@ng-bootstrap/ng-bootstrap';
import {ConcessionCarouselModule} from './concession-carousel/concession-carousel.module';


@NgModule({
  declarations: [
    ConcessionsComponent,
    ConcessionItemComponent,
    NoRegistrationComponent,
    AskLoginComponent,
    CheckoutComponent,
  ],
  imports: [
    CommonModule,
    OrderRoutingModule,
    ReactiveFormsModule,
    AuthModule,
    ConcessionCarouselModule,
    NgbTimepickerModule,
  ],
  providers: [
    [OrderService]
  ],
  exports: []
})

export class OrderModule { }
