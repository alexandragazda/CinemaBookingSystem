import { Component, OnInit } from '@angular/core';
import {OrderService} from '../order-service';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {FormBuilder, FormControl} from '@angular/forms';
import {OrderData} from '../../entities/OrderData';

@Component({
  selector: 'app-pick-up',
  templateUrl: './pick-up.component.html',
  styleUrls: ['./pick-up.component.css']
})
export class PickUpComponent implements OnInit {

  orderData: OrderData;
  minDate: Date;
  maxDate: Date;
  minTime: any;
  maxTime: any;
  ctrl;
  pickUpPara: boolean;

  // tslint:disable-next-line:max-line-length
  constructor(private orderService: OrderService, private authService: AuthService, private router: Router, private datePipe: DatePipe, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime,  this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, this.orderData.orderDataList, this.orderData.totalPrice, null, this.orderData.userInfo);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.pickUpPara = false;

    const dateString = this.orderData.showtimeDate + 'T' + this.orderData.showtimeTime;
    const date = new Date(dateString);
    this.maxDate = new Date(date.getTime() - 10000 * 60); // max is before 10 minutes
    this.maxTime = this.datePipe.transform(this.maxDate, 'HH:mm');
    this.minDate = new Date(date.getTime() - 30000 * 60); // min is before 30 minutes
    const now = new Date(); // !!!!!!!!!!! today (urmatoarele 2 randuri dispar)
    // now.setTime(new Date().getTime());
    // now.setFullYear(2020, 2, 19);
    if (this.minDate.getDate() === now.getDate() && this.minDate.getTime() <= now.getTime()) {
      this.minTime = this.datePipe.transform(now.getTime(), 'HH:mm');
    } else {
      this.minTime = this.datePipe.transform(this.minDate, 'HH:mm');
    }

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
  }

  nextStep() {
    if (this.ctrl.invalid) {
      this.pickUpPara = true;
      return;
    }

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
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, this.orderData.orderDataList, this.orderData.totalPrice, time, this.orderData.userInfo);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    if (this.authService.getToken() !== null || this.orderData.userInfo !== null) {
      this.router.navigate(['order/checkout']);
    } else {
      this.router.navigate(['order/account']);
    }
  }
}
