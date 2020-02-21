import { Component, OnInit } from '@angular/core';
import {BookingData} from '../../entities/BookingData';
import * as jwt_decode from 'jwt-decode';
import {AuthService} from '../../auth/auth-service';
import {BookingService} from '../booking-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-successful-booking',
  templateUrl: './successful-booking.component.html',
  styleUrls: ['./successful-booking.component.css']
})
export class SuccessfulBookingComponent implements OnInit {

  bookingData: BookingData;

  constructor(private bookingService: BookingService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));

    let decoded; let userEmail;
    if (this.authService.getToken() !== null) {
      decoded = jwt_decode(this.authService.getToken());
      userEmail = decoded.sub;
      // tslint:disable-next-line:max-line-length
      this.bookingService.bookingEmail(this.bookingData.showtimeID, userEmail, null, null, null, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 4}});
        });
    } else {
      userEmail = null;
      // tslint:disable-next-line:max-line-length
      this.bookingService.bookingEmail(this.bookingData.showtimeID, userEmail, this.bookingData.userInfo.email, this.bookingData.userInfo.firstName, this.bookingData.userInfo.lastName, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats)
        .subscribe((res) => {
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code: 4}});
        });
    }
  }

}
