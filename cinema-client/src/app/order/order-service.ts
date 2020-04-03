import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Concession} from '../entities/Concession';
import {OrderItem} from '../entities/OrderData';

const URL = 'http://localhost:3000';
const getConcessionsURL = `${URL}/concessions`;
const orderURL = `${URL}/orders`;

@Injectable({
  providedIn: 'root'
})

export class OrderService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {
  }

  getConcessions() {
    return this.httpClient.get<Concession[]>(getConcessionsURL, {headers: this.headers})
      .pipe(tap(response => {
      }));
  }

  // tslint:disable-next-line:max-line-length
  checkout(showtimeID: number, userEmail: string, customerEmail: string, customerFirstName: string, customerLastName: string, orderItems: Array<OrderItem>, totalPrice: number, pickUpTime: string) {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.post(orderURL, {showtimeID, userEmail, customerEmail, customerFirstName, customerLastName, orderItems, totalPrice, pickUpTime}, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }

  deleteOrder(id: string) {
    const url = `${URL}/orders/${id}`;
    return this.httpClient.delete(url, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }
}
