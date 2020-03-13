import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {BookingService} from '../booking-service';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderData} from '../../entities/OrderData';
import {BookingData} from '../../entities/BookingData';

@Component({
  selector: 'app-successful-booking',
  templateUrl: './successful-booking.component.html',
  styleUrls: ['./successful-booking.component.css']
})
export class SuccessfulBookingComponent implements OnInit {

  code: number;
  bookingData: BookingData;
  orderData: OrderData;

  // tslint:disable-next-line:max-line-length
  constructor(private bookingService: BookingService, private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.code = params.code;
    });

    // this.bookingService.bookingEmail(this.code)
    //     .subscribe((res) => {
    //     }, (error) => {
    //       this.router.navigate(['/error'], {queryParams: {code : 5}});
    //     });
  }

  order() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime,  this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, null, 0, null, this.bookingData.userInfo);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.router.navigate(['/order/concessions']);
  }

}
