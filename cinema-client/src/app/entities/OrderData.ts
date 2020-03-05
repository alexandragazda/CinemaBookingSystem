import {Customer} from './Customer';
import {Time} from '@angular/common';
import {Technology} from './Showtime';
import {AgeRating} from './Movie';

export class OrderData {
  showtimeID: number;
  showtimeDate: Date;
  showtimeTime: Time;
  showtimeTechnology: Technology;
  showtimeScreen: number;
  movieTitle: string;
  ageRating: AgeRating;
  orderDataList: OrderItemDetails[];
  totalPrice: number;
  pickUpTime: string;
  userInfo: Customer;


  // tslint:disable-next-line:max-line-length
  constructor(showtimeID: number, showtimeDate: Date, showtimeTime: Time, showtimeTechnology: Technology, showtimeScreen: number, movieTitle: string, ageRating: AgeRating, orderDataList: OrderItemDetails[], totalPrice: number, pickUpTime: string, userInfo: Customer) {
    this.showtimeID = showtimeID;
    this.showtimeDate = showtimeDate;
    this.showtimeTime = showtimeTime;
    this.showtimeTechnology = showtimeTechnology;
    this.showtimeScreen = showtimeScreen;
    this.movieTitle = movieTitle;
    this.ageRating = ageRating;
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
