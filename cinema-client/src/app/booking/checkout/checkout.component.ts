import { Component, OnInit } from '@angular/core';
import {BookingData} from '../../entities/BookingData';
import {BookingService} from '../booking-service';
import * as jwt_decode from 'jwt-decode';
import {AuthService} from '../../auth/auth-service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  bookingData: BookingData;

  constructor(private bookingService: BookingService, private authService: AuthService) { }

  ngOnInit() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
  }

  checkout() {
    let decoded; let userEmail;
    if (this.authService.getToken() !== null) {
      decoded = jwt_decode(this.authService.getToken());
      userEmail = decoded.sub;
      // tslint:disable-next-line:max-line-length
      this.bookingService.checkout(this.bookingData.showtimeID, userEmail, null, null, null, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
        });
    } else {
      userEmail = null;
      // tslint:disable-next-line:max-line-length
      this.bookingService.checkout(this.bookingData.showtimeID, userEmail, this.bookingData.userInfo.email, this.bookingData.userInfo.firstName, this.bookingData.userInfo.lastName, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
        });
    }
    //
    // // tslint:disable-next-line:max-line-length
    // tslint:disable-next-line:max-line-length
    // this.bookingService.checkout(this.bookingData.showtimeID, userEmail, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
    //   .subscribe((res) => {
    //       // sessionStorage.clear();
    //   });
  }
}
