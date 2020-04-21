import { Component, OnInit } from '@angular/core';
import {OrderData, OrderItem} from '../../entities/OrderData';
import {OrderService} from '../order-service';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import {DatePipe} from '@angular/common';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  orderData: OrderData;
  customerFirstName = '';
  customerLastName = '';
  customerEmail = '';
  orderItems = new Array<OrderItem>();

  // tslint:disable-next-line:max-line-length
  constructor(private orderService: OrderService, private authService: AuthService, private router: Router, private datePipe: DatePipe, private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.spinnerService.hide();

    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));

    if (this.orderData.userInfo !== null) {
      this.customerEmail = this.orderData.userInfo.email;
      this.customerFirstName = this.orderData.userInfo.firstName;
      this.customerLastName = this.orderData.userInfo.lastName;
    }

    this.orderData.orderDataList.forEach(x => {
      const orderItem = new OrderItem(x.concessionID, x.qty);
      this.orderItems.push(orderItem);
    });
  }

  finish() {
    this.spinnerService.show();

    let decoded; let userEmail;
    if (this.authService.getToken() !== null) {
      decoded = jwt_decode(this.authService.getToken());
      userEmail = decoded.sub;
      // tslint:disable-next-line:max-line-length
      this.orderService.placeOrder(this.orderData.showtimeID, userEmail, null, null, null, this.orderItems, this.orderData.totalPrice, this.orderData.pickupTime)
        .subscribe((res) => {
          this.router.navigate(['/order/successful-order'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 4}});
        });
    } else {
      userEmail = null;
      // tslint:disable-next-line:max-line-length
      this.orderService.placeOrder(this.orderData.showtimeID, userEmail, this.orderData.userInfo.email, this.orderData.userInfo.firstName, this.orderData.userInfo.lastName, this.orderItems, this.orderData.totalPrice, this.orderData.pickupTime)
        .subscribe((res) => {
          this.router.navigate(['/order/successful-order'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code: 4}});
        });
    }
  }
}
