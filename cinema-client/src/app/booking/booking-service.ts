import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {TicketType} from '../entities/TicketType';
import {TicketTypeDTO} from '../entities/TicketTypeDTO';
// import {BookingDTO} from '../entities/BookingDTO';

const URL = 'http://localhost:3000';
const getTicketsURL = `${URL}/tickettypes`;
const getSeatsURL = `${URL}/seats`;
const bookingURL = `${URL}/bookings`;

@Injectable({
  providedIn: 'root'
})

export class BookingService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {
  }

  getTickets(showtimeID: string) {
    return this.httpClient.get<TicketTypeDTO>(getTicketsURL, {headers: this.headers, params: {showtimeID}})
      .pipe(tap(response => {
      }));
  }

  getSeats(showtimeID: string) {
  // getSeats(screenID: string, date: string, time: string) {
    return this.httpClient.get<Array<Array<number>>>(getSeatsURL, {headers: this.headers, params: {showtimeID}})
      .pipe(tap(response => {
      }));
  }

  // tslint:disable-next-line:max-line-length
  checkout(showtimeID: number, userEmail: string, customerEmail: string, customerFirstName: string, customerLastName: string, nrChildTickets: number, nrStudentTickets: number, nrAdultTickets: number, nrRetiredTickets: number, totalPrice: number, selectedSeats: string) {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.post(bookingURL, {showtimeID, userEmail, customerEmail, customerFirstName, customerLastName, nrChildTickets, nrStudentTickets, nrAdultTickets, nrRetiredTickets, totalPrice, selectedSeats}, {headers: this.headers})
      .pipe(tap(respone => {

      }));
  }
}
