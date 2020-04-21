import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {Customer} from '../../../entities/Customer';
import {OrderData} from '../../../entities/OrderData';

@Component({
  selector: 'app-no-registration',
  templateUrl: './no-registration.component.html',
  styleUrls: ['./no-registration.component.css']
})
export class NoRegistrationComponent implements OnInit {

  guestForm;
  submitted = false;
  orderData: OrderData;

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.guestForm =  this.formBuilder.group({
      email: [ '' , [Validators.required, Validators.email]],
      firstName: [ '' , Validators.required],
      lastName: [ '' , Validators.required],
    });

    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, this.orderData.orderDataList, this.orderData.totalPrice, this.orderData.pickupTime, null);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));
  }

  get f() {
    return this.guestForm.controls;
  }

  guest() {
    this.submitted = true;

    if (this.guestForm.invalid) {
      return;
    }

    const userInfo = new Customer(this.f.email.value, this.f.firstName.value, this.f.lastName.value);
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, this.orderData.orderDataList, this.orderData.totalPrice, this.orderData.pickupTime, userInfo);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.router.navigate(['/order/confirmation']);
  }
}
