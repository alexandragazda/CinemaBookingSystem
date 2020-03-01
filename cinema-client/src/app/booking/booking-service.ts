import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {TicketTypeDTO} from '../entities/TicketTypeDTO';

const URL = 'http://localhost:3000';
const getTicketsURL = `${URL}/tickettypes`;
const getSeatsURL = `${URL}/seats`;
const bookingURL = `${URL}/bookings`;
const bookingEmailURL = `${URL}/bookingEmail`;
const getNrAvailableSeatsURL = `${URL}/nrAvailableSeats`;

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
    return this.httpClient.get<Array<Array<number>>>(getSeatsURL, {headers: this.headers, params: {showtimeID}})
      .pipe(tap(response => {
      }));
  }

  // tslint:disable-next-line:max-line-length
  checkout(showtimeID: number, userEmail: string, customerEmail: string, customerFirstName: string, customerLastName: string, nrChildTickets: number, nrStudentTickets: number, nrAdultTickets: number, nrRetiredTickets: number, totalPrice: number, selectedSeats: string) {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.post(bookingURL, {showtimeID, userEmail, customerEmail, customerFirstName, customerLastName, nrChildTickets, nrStudentTickets, nrAdultTickets, nrRetiredTickets, totalPrice, selectedSeats}, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }

  bookingEmail(code: number) {
    return this.httpClient.post(bookingEmailURL,
      {code}, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }

  getNrAvailableSeats(showtimeID: string) {
    return this.httpClient.get<number>(getNrAvailableSeatsURL, {headers: this.headers, params: {showtimeID}})
      .pipe(tap(response => {
      }));
  }
}
