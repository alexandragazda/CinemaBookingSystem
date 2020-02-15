import {Component, Input, OnInit} from '@angular/core';
import {TicketType} from '../../../entities/TicketType';
import {BookingData} from '../../../entities/BookingData';

@Component({
  selector: 'app-ticket-item',
  templateUrl: './ticket-item.component.html',
  styleUrls: ['./ticket-item.component.css']
})
export class TicketItemComponent implements OnInit {

  @Input() ticket: TicketType;

  bookingData: BookingData;

  constructor() { }

  ngOnInit() {}

  incrementValue() {
    const stringValue = document.getElementById(this.ticket.id.toString()).getAttribute('value');
    const value = Number(stringValue) + 1;
    document.getElementById(this.ticket.id.toString()).setAttribute('value', value.toString());

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    let nrChildTicket = this.bookingData.nrChildTicket;
    let nrStudentTicket = this.bookingData.nrStudentTicket;
    let nrAdultTicket = this.bookingData.nrAdultTicket;
    let nrRetiredTicket = this.bookingData.nrRetiredTicket;

    if ( this.ticket.id.toString() === 'Child') {
      nrChildTicket = value;
    } else if (this.ticket.id.toString() === 'Student') {
      nrStudentTicket = value;
    } else if ( this.ticket.id.toString() === 'Adult') {
      nrAdultTicket = value;
    } else if (this.ticket.id.toString() === 'Retired') {
      nrRetiredTicket = value;
    }

    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, nrChildTicket, nrStudentTicket, nrAdultTicket, nrRetiredTicket, 0);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));
    // console.log('nrChildTicket ' + this.bookingData.nrChildTicket);
    // console.log('nrStudentTicket ' + this.bookingData.nrStudentTicket);
    // console.log('nrAdultTicket ' + this.bookingData.nrAdultTicket);
    // console.log('nrRetiredTicket ' + this.bookingData.nrRetiredTicket);
  }

  decrementValue() {
    const stringValue = document.getElementById(this.ticket.id.toString()).getAttribute('value');
    const value = Number(stringValue) - 1;

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    let nrChildTicket = this.bookingData.nrChildTicket;
    let nrStudentTicket = this.bookingData.nrStudentTicket;
    let nrAdultTicket = this.bookingData.nrAdultTicket;
    let nrRetiredTicket = this.bookingData.nrRetiredTicket;

    if (value >= 0 ) {
      document.getElementById(this.ticket.id.toString()).setAttribute('value', value.toString());

      if ( this.ticket.id.toString() === 'Child') {
        nrChildTicket = value;
      } else if (this.ticket.id.toString() === 'Student') {
        nrStudentTicket = value;
      } else if ( this.ticket.id.toString() === 'Adult') {
        nrAdultTicket = value;
       } else if (this.ticket.id.toString() === 'Retired') {
        nrRetiredTicket = value;
      }
    }

    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, nrChildTicket, nrStudentTicket, nrAdultTicket, nrRetiredTicket, 0);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));
    // console.log('nrChildTicket ' + this.bookingData.nrChildTicket);
    // console.log('nrStudentTicket ' + this.bookingData.nrStudentTicket);
    // console.log('nrAdultTicket ' + this.bookingData.nrAdultTicket);
    // console.log('nrRetiredTicket ' + this.bookingData.nrRetiredTicket);
  }
}
