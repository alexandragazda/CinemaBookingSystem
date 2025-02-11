import {Component, OnInit} from '@angular/core';
import {TicketType} from '../../entities/TicketType';
import {BookingService} from '../booking-service';
import {BookingData} from '../../entities/BookingData';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: TicketType[];
  nrAvailableTickets: number;
  bookingData: BookingData;
  ticketsPara: boolean;
  maxSeatsPara: boolean;

  constructor(private bookingService: BookingService, private datePipe: DatePipe, private router: Router) { }

  ngOnInit() {

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, 0, 0, 0, 0, 0, null, this.bookingData.userInfo);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));

    this.ticketsPara = false;

    return this.bookingService.getTickets(this.bookingData.showtimeID.toString())
      .subscribe(data => {
        this.tickets = data.ticketTypeList;
        this.nrAvailableTickets = data.nrAvailableTickets;
        if (this.nrAvailableTickets === 0) {
          this.router.navigate(['/error'], {queryParams: {code: 1}});
        }
      });
  }

  seats() {
    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    console.log('nrChildTicket ' + this.bookingData.nrChildTicket);
    console.log('nrStudentTicket ' + this.bookingData.nrStudentTicket);
    console.log('nrAdultTicket ' + this.bookingData.nrAdultTicket);
    console.log('nrRetiredTicket ' + this.bookingData.nrRetiredTicket);

    const totalNrOfSelectedTickets = this.bookingData.nrChildTicket +
                                     this.bookingData.nrStudentTicket +
                                     this.bookingData.nrAdultTicket +
                                     this.bookingData.nrRetiredTicket;
    if (totalNrOfSelectedTickets > this.nrAvailableTickets) {
      this.maxSeatsPara = true;
      this.ticketsPara = false;
      const msg = 'You can\'t select ' + totalNrOfSelectedTickets + ' tickets, as ';
      if (this.nrAvailableTickets > 1) {
        document.getElementById('maxSeatsPara').innerText = msg + 'there are ' + this.nrAvailableTickets + ' tickets left!';
      } else if (this.nrAvailableTickets === 1) {
        document.getElementById('maxSeatsPara').innerText = msg + 'there is ' + this.nrAvailableTickets + ' ticket left!';
      }
    } else {
      let totalPrice = 0;
      if (this.bookingData.technology.toString() === 'tec_2D') {
        totalPrice =
          this.bookingData.nrChildTicket * this.tickets[0].price2D +
          this.bookingData.nrStudentTicket * this.tickets[1].price2D +
          this.bookingData.nrAdultTicket * this.tickets[2].price2D +
          this.bookingData.nrRetiredTicket * this.tickets[3].price2D;
      } else if (this.bookingData.technology.toString() === 'tec_3D') {
        totalPrice =
          this.bookingData.nrChildTicket * this.tickets[0].price3D +
          this.bookingData.nrStudentTicket * this.tickets[1].price3D +
          this.bookingData.nrAdultTicket * this.tickets[2].price3D +
          this.bookingData.nrRetiredTicket * this.tickets[3].price3D;
      }

      if (totalPrice === 0) {
        this.ticketsPara = true;
        this.maxSeatsPara = false;
      } else {
        // tslint:disable-next-line:max-line-length
        this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, totalPrice, null, this.bookingData.userInfo);
        sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));
        console.log('totalPrice ' + totalPrice);

        this.router.navigate(['/booking/seats']);
      }

    }
  }

}
