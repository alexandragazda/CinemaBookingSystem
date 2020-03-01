import { Component, OnInit } from '@angular/core';
import {OrderData, OrderItem} from '../../entities/OrderData';
import {OrderService} from '../order-service';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  orderData: OrderData;
  customerFirstName = '';
  customerLastName = '';
  customerEmail = '';
  orderItems = new Array<OrderItem>();

  constructor(private orderService: OrderService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
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

  checkout() {
    let decoded; let userEmail;
    if (this.authService.getToken() !== null) {
      decoded = jwt_decode(this.authService.getToken());
      userEmail = decoded.sub;
      // tslint:disable-next-line:max-line-length
      this.orderService.checkout(this.orderData.showtimeID, userEmail, null, null, null, this.orderItems, this.orderData.totalPrice, this.orderData.pickUpTime)
        .subscribe((res) => {
          // this.router.navigate(['/booking/successful-booking'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 3}});
        });
    } else {
      userEmail = null;
      // tslint:disable-next-line:max-line-length
      this.orderService.checkout(this.orderData.showtimeID, userEmail, this.orderData.userInfo.email, this.orderData.userInfo.firstName, this.orderData.userInfo.lastName, this.orderItems, this.orderData.totalPrice, this.orderData.pickUpTime)
        .subscribe((res) => {
          // this.router.navigate(['/booking/successful-booking'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code: 3}});
        });
    }
  }
}
