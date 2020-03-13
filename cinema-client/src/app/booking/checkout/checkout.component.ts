import { Component, OnInit } from '@angular/core';
import {BookingData} from '../../entities/BookingData';
import {BookingService} from '../booking-service';
import * as jwt_decode from 'jwt-decode';
import {AuthService} from '../../auth/auth-service';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  bookingData: BookingData;
  seats = new Array<string>();
  customerFirstName = '';
  customerLastName = '';
  customerEmail = '';

  // tslint:disable-next-line:max-line-length
  constructor(private bookingService: BookingService, private authService: AuthService, private datePipe: DatePipe, private router: Router, private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.spinnerService.hide();

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));

    if (this.bookingData.userInfo !== null) {
      this.customerEmail = this.bookingData.userInfo.email;
      this.customerFirstName = this.bookingData.userInfo.firstName;
      this.customerLastName = this.bookingData.userInfo.lastName;
    }

    let i; let j;
    const parsedSeats = this.bookingData.selectedSeats.split(';');
    for (i = 0; i < parsedSeats.length; i++) {
      let result = '';
      const rowcols = parsedSeats[i].split(':');
      result += String(Number(rowcols[0]) + 1) + ':';
      const cols =  rowcols[1].split(',');
      for (j = 0; j < cols.length; j++) {
        result += Number(cols[j]) + 1 + ',';
      }

      result = result.substr(0, result.length - 1);
      this.seats.push(result);
    }
  }

  checkout() {
    this.spinnerService.show();

    let decoded; let userEmail;
    if (this.authService.getToken() !== null) {
      decoded = jwt_decode(this.authService.getToken());
      userEmail = decoded.sub;
      // tslint:disable-next-line:max-line-length
      this.bookingService.checkout(this.bookingData.showtimeID, userEmail, null, null, null, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
          this.router.navigate(['/booking/successful-booking'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 3}});
        });
    } else {
      userEmail = null;
      // tslint:disable-next-line:max-line-length
      this.bookingService.checkout(this.bookingData.showtimeID, userEmail, this.bookingData.userInfo.email, this.bookingData.userInfo.firstName, this.bookingData.userInfo.lastName, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
          this.router.navigate(['/booking/successful-booking'], {queryParams: {code: res}});
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code: 3}});
        });
    }
  }
}
