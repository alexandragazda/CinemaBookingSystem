import { Component, OnInit } from '@angular/core';
import {BookingService} from '../../../booking/booking-service';
import {BookingData} from '../../../entities/BookingData';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ask-booking-order',
  templateUrl: './ask-booking-order.component.html',
  styleUrls: ['./ask-booking-order.component.css']
})
export class AskBookingOrderComponent implements OnInit {

  bookingData: BookingData;
  nrAvailableSeats: number;
  selectedItem: number;

  constructor(private bookingService: BookingService, private router: Router) { }

  ngOnInit() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));

    return this.bookingService.getNrAvailableSeats(this.bookingData.showtimeID.toString())
      .subscribe(data => {
        this.nrAvailableSeats = data;
    });
  }

  onItemChange(selectedItem: number) {
    this.selectedItem = selectedItem;
  }

  choose() {
    if ( this.selectedItem === 0 ) {
      this.router.navigate(['booking/tickets']);
    } else if (this.selectedItem === 1) {
      this.router.navigate(['order/concessions']);
    }
  }

}
