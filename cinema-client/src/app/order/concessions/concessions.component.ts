import { Component, OnInit } from '@angular/core';
import {OrderService} from '../order-service';
import {Concession} from '../../entities/Concession';
import {OrderData, OrderItemDetails} from '../../entities/OrderData';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {FormBuilder, Validators} from '@angular/forms';

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
  concessionForm;
  submitted = false;
  selectItemsPara: boolean;
  wrongPickUpPara: boolean;

  // tslint:disable-next-line:max-line-length
  constructor(private orderService: OrderService, private authService: AuthService, private router: Router, private datePipe: DatePipe, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, null, 0, null, null);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.selectItemsPara = false;
    this.wrongPickUpPara = false;

    const dateString = this.orderData.showtimeDate + 'T' + this.orderData.showtimeTime;
    const date = new Date(dateString);
    this.minDate = new Date(date.getTime() - 30000 * 60); // min is before 30 minutes
    this.maxDate = new Date(date.getTime() - 10000 * 60); // max is before 10 minutes
    this.minTime = this.datePipe.transform(this.minDate, 'HH:mm');
    this.maxTime = this.datePipe.transform(this.maxDate, 'HH:mm');

    this.concessionForm = this.formBuilder.group({
      pickUpInput: [this.minTime, [Validators.required]],
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

  get f() {
    return this.concessionForm.controls;
  }


  checkout() {
    this.submitted = true;

    if (this.concessionForm.invalid) {
      return;
    }

    const dateString = this.orderData.showtimeDate + 'T' + this.f.pickUpInput.value;
    const date = new Date(dateString);
    if (date.getTime() < this.minDate.getTime() || date.getTime() > this.maxDate.getTime()) {
      this.wrongPickUpPara = true;
    } else {
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
      } else {
        let totalPrice = 0;
        returnedList.forEach(x => {
          totalPrice += x.concessionPrice * x.qty;
        });

        window.alert(this.f.pickUpInput.value);
        // tslint:disable-next-line:max-line-length
        this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, returnedList, totalPrice, this.f.pickUpInput.value, null);
        sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

        if (this.authService.getToken() !== null) {
          this.router.navigate(['order/checkout']);
        } else {
          this.router.navigate(['order/account']);
        }
      }
    }
  }
}
