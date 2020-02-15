import { Component, OnInit } from '@angular/core';
import {TicketType} from '../../entities/TicketType';
import {BookingService} from '../booking-service';
import {BookingData} from '../../entities/BookingData';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: TicketType[];

  bookingData: BookingData;
  ticketsPara: boolean;

  constructor(private bookingService: BookingService) { }

  ngOnInit() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));

    this.ticketsPara = false;

    return this.bookingService.getTickets()
      .subscribe(data => {
        this.tickets = data;
      });
  }

  seats() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    console.log('nrChildTicket ' + this.bookingData.nrChildTicket);
    console.log('nrStudentTicket ' + this.bookingData.nrStudentTicket);
    console.log('nrAdultTicket ' + this.bookingData.nrAdultTicket);
    console.log('nrRetiredTicket ' + this.bookingData.nrRetiredTicket);

    const totalPrice =
      this.bookingData.nrChildTicket * this.tickets[0].price +
      this.bookingData.nrStudentTicket * this.tickets[1].price +
      this.bookingData.nrAdultTicket * this.tickets[2].price +
      this.bookingData.nrRetiredTicket * this.tickets[3].price;

    if (totalPrice === 0) {
      this.ticketsPara = true;
    } else {
      // tslint:disable-next-line:max-line-length
      this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, totalPrice);
      sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));
      console.log('totalPrice ' + totalPrice);
    }
  }

}
