import {Technology} from './Showtime';
import {Time} from '@angular/common';

export class BookingInfoDTO {
  bookingID: number;
  nrChildTickets: number;
  nrStudentTickets: number;
  nrAdultTickets: number;
  nrRetiredTickets: number;
  totalPrice: number;
  seats: string;
  movieTitle: string;
  movieTechnology: Technology;
  showtimeDate: Date;
  showtimeTime: Time;
  screenID: number;
}
