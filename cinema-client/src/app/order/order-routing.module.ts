import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ConcessionsComponent} from './concessions/concessions.component';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {ConfirmationComponent} from './confirmation/confirmation.component';
import {SuccessfulOrderComponent} from './successful-order/successful-order.component';
import {PickUpComponent} from './pick-up/pick-up.component';

const routes: Routes = [
  {
    path: 'concessions',
    component: ConcessionsComponent,
  },
  {
    path: 'pickup-time',
    component: PickUpComponent,
  },
  {
    path: 'account',
    component: AskLoginComponent,
  },
  {
    path: 'confirmation',
    component: ConfirmationComponent,
  },
  {
    path: 'successful-order',
    component: SuccessfulOrderComponent,
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
export class OrderRoutingModule { }
