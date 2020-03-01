import {Customer} from './Customer';
import {Time} from '@angular/common';

export class OrderData {
  showtimeID: number;
  showtimeDate: Date;
  showtimeTime: Time;
  orderDataList: OrderItemDetails[];
  totalPrice: number;
  pickUpTime: Time;
  userInfo: Customer;


  // tslint:disable-next-line:max-line-length
  constructor(showtimeID: number, showtimeDate: Date, showtimeTime: Time, orderDataList: OrderItemDetails[], totalPrice: number, pickUpTime: Time, userInfo: Customer) {
    this.showtimeID = showtimeID;
    this.showtimeDate = showtimeDate;
    this.showtimeTime = showtimeTime;
    this.orderDataList = orderDataList;
    this.totalPrice = totalPrice;
    this.pickUpTime = pickUpTime;
    this.userInfo = userInfo;
  }
}

export class OrderItemDetails {
  concessionID: number;
  concessionName: string;
  concessionDescription: string;
  concessionPrice: number;
  qty: number;


  constructor(concessionID: number, concessionName: string, concessionDescription: string, concessionPrice: number, qty: number) {
    this.concessionID = concessionID;
    this.concessionName = concessionName;
    this.concessionDescription = concessionDescription;
    this.concessionPrice = concessionPrice;
    this.qty = qty;
  }
}

export class OrderItem {
  concessionID: number;
  qty: number;


  constructor(concessionID: number, qty: number) {
    this.concessionID = concessionID;
    this.qty = qty;
  }
}
