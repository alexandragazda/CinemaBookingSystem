import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {TicketType} from '../entities/TicketType';

const URL = 'http://localhost:3000';
const getTicketsURL = `${URL}/tickets`;

@Injectable({
  providedIn: 'root'
})

export class BookingService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {
  }

  getTickets() {
    return this.httpClient.get<TicketType[]>(getTicketsURL, {headers: this.headers})
      .pipe(tap(response => {
      }));
  }
}
