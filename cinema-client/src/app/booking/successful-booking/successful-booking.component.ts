import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {BookingService} from '../booking-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-successful-booking',
  templateUrl: './successful-booking.component.html',
  styleUrls: ['./successful-booking.component.css']
})
export class SuccessfulBookingComponent implements OnInit {

  code: number;

  // tslint:disable-next-line:max-line-length
  constructor(private bookingService: BookingService, private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.code = params.code;
    });

    this.bookingService.bookingEmail(this.code)
        .subscribe((res) => {
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 4}});
        });
  }

}
