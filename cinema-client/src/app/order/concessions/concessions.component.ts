import { Component, OnInit } from '@angular/core';
import {OrderService} from '../order-service';
import {Concession} from '../../entities/Concession';
import {OrderData, OrderItemDetails} from '../../entities/OrderData';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {FormBuilder, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-concessions',
  templateUrl: './concessions.component.html',
  styleUrls: ['./concessions.component.css']
})
export class ConcessionsComponent implements OnInit {

  concessions: Concession[];
  food = new Array<Concession>();
  drink = new Array<Concession>();
  menus = new Array<Concession>();
  orderData: OrderData;
  minDate: Date;
  maxDate: Date;
  minTime: any;
  maxTime: any;
  ctrl;
  selectItemsPara: boolean;
  pickUpPara: boolean;

  // tslint:disable-next-line:max-line-length
  constructor(private orderService: OrderService, private authService: AuthService, private router: Router, private datePipe: DatePipe, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime,  this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, null, 0, null, null);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.selectItemsPara = false;
    this.pickUpPara = false;

    const dateString = this.orderData.showtimeDate + 'T' + this.orderData.showtimeTime;
    const date = new Date(dateString);
    this.minDate = new Date(date.getTime() - 30000 * 60); // min is before 30 minutes
    this.maxDate = new Date(date.getTime() - 10000 * 60); // max is before 10 minutes
    this.minTime = this.datePipe.transform(this.minDate, 'HH:mm');
    this.maxTime = this.datePipe.transform(this.maxDate, 'HH:mm');

    this.ctrl = new FormControl('', (control: FormControl) => {
      const value = control.value;

      if (!value) {
        return null;
      }

      const minHour = Number(this.minTime.toString().split(':')[0]);
      const minMin = Number(this.minTime.toString().split(':')[1]);
      const maxHour = Number(this.maxTime.toString().split(':')[0]);
      const maxMin = Number(this.maxTime.toString().split(':')[1]);
      if (value.hour < minHour) {
        return {tooEarly: true};
      }
      if (value.hour > maxHour) {
        return {tooLate: true};
      }
      if (value.hour === minHour && value.minute < minMin) {
        return {tooEarly: true};
      }
      if (value.hour === maxHour && value.minute > maxMin) {
        return {tooLate: true};
      }

      return null;
    });

    return this.orderService.getConcessions()
      .subscribe(data => {
        this.concessions = data;
        data.forEach(x => {
          if (x.concessionType.id === 'Food') {
            this.food.push(x);
          } else if (x.concessionType.id === 'Drink') {
            this.drink.push(x);
          } else if (x.concessionType.id === 'Menu') {
            this.menus.push(x);
          }
        });
      });
  }

  checkout() {

    if (this.ctrl.invalid) {
      this.pickUpPara = true;
      this.selectItemsPara = false;
      return;
    }

    const returnedList = new Array<OrderItemDetails>();

    this.concessions.forEach(x => {
      const value = document.getElementById(x.id.toString()).getAttribute('value');
      if (Number(value) !== 0) {
        const orderItem = new OrderItemDetails(x.id, x.name, x.description, x.price, Number(value));
        returnedList.push(orderItem);
      }
    });

    if (returnedList.length === 0) {
      this.selectItemsPara = true;
      this.pickUpPara = false;
    } else {
      this.selectItemsPara = false;

      let totalPrice = 0;
      returnedList.forEach(x => {
        totalPrice += x.concessionPrice * x.qty;
      });

      let minute = ''; let hour = '';
      if (this.ctrl.value.minute.toString().length === 1) {
        minute = '0' + this.ctrl.value.minute;
      } else {
        minute = this.ctrl.value.minute;
      }
      if (this.ctrl.value.hour.toString().length === 1) {
        hour = '0' + this.ctrl.value.hour;
      } else {
        hour = this.ctrl.value.hour;
      }
      const time = hour + ':' + minute;
      // tslint:disable-next-line:max-line-length
      this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, returnedList, totalPrice, time, null);
      sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

      if (this.authService.getToken() !== null) {
        this.router.navigate(['order/checkout']);
      } else {
        this.router.navigate(['order/account']);
      }
    }
  }
}
