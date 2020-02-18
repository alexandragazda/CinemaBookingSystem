import {Showtime, Technology} from './Showtime';
import {Time} from '@angular/common';
import {AgeRating} from './Movie';
import {Customer} from './Customer';

export class BookingData {
  showtimeID: number;
  movieTitle: string;
  moviePoster: any;
  technology: Technology;
  screen: number;
  date: Date;
  time: Time;
  ageRating: AgeRating;
  nrChildTicket: number;
  nrStudentTicket: number;
  nrAdultTicket: number;
  nrRetiredTicket: number;
  totalPrice: number;
  selectedSeats: string;
  userInfo: Customer;

  // tslint:disable-next-line:max-line-length
  constructor(showtimeID: number, movieTitle: string, moviePoster: any, technology: Technology, screen: number, date: Date, time: Time, ageRating: AgeRating, nrChildTicket: number, nrStudentTicket: number, nrAdultTicket: number, nrRetiredTicket: number, totalPrice: number, selectedSeats: string, userInfo: Customer) {
    this.showtimeID = showtimeID;
    this.movieTitle = movieTitle;
    this.moviePoster = moviePoster;
    this.technology = technology;
    this.screen = screen;
    this.date = date;
    this.time = time;
    this.ageRating = ageRating;
    this.nrChildTicket = nrChildTicket;
    this.nrStudentTicket = nrStudentTicket;
    this.nrAdultTicket = nrAdultTicket;
    this.nrRetiredTicket = nrRetiredTicket;
    this.totalPrice = totalPrice;
    this.selectedSeats = selectedSeats;
    this.userInfo = userInfo;
  }
}

// export class BookingData {
//   showtime: Showtime;
//   nrChildTicket: number;
//   nrStudentTicket: number;
//   nrAdultTicket: number;
//   nrRetiredTicket: number;
//   totalPrice: number;
//
//
//   // tslint:disable-next-line:max-line-length
// tslint:disable-next-line:max-line-length
//   constructor(showtime: Showtime, nrChildTicket: number, nrStudentTicket: number, nrAdultTicket: number, nrRetiredTicket: number, totalPrice: number) {
//     this.showtime = showtime;
//     this.nrChildTicket = nrChildTicket;
//     this.nrStudentTicket = nrStudentTicket;
//     this.nrAdultTicket = nrAdultTicket;
//     this.nrRetiredTicket = nrRetiredTicket;
//     this.totalPrice = totalPrice;
//   }
// }
