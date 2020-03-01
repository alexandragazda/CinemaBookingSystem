import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ConcessionsComponent} from './concessions/concessions.component';
import {AskLoginComponent} from './ask-login/ask-login.component';
import {CheckoutComponent} from './checkout/checkout.component';

const routes: Routes = [
  {
    path: 'concessions',
    component: ConcessionsComponent,
  },
  {
    path: 'account',
    component: AskLoginComponent,
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
export class OrderRoutingModule { }
