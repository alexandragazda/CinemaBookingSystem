import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Concession} from '../entities/Concession';
import {OrderItem} from '../entities/OrderData';
import {Time} from '@angular/common';

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

  // getConcessionsByType(concessionType: string) {
  //   return this.httpClient.get<Concession[]>(getConcessionsURL, {headers: this.headers, params: {concessionType}})
  //     .pipe(tap(response => {
  //     }));
  // }

  getConcessions() {
    return this.httpClient.get<Concession[]>(getConcessionsURL, {headers: this.headers})
      .pipe(tap(response => {
      }));
  }

  // tslint:disable-next-line:max-line-length
  checkout(showtimeID: number, userEmail: string, customerEmail: string, customerFirstName: string, customerLastName: string, orderItems: Array<OrderItem>, totalPrice: number, pickUpTime: Time) {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.post(orderURL, {showtimeID, userEmail, customerEmail, customerFirstName, customerLastName, orderItems, totalPrice, pickUpTime}, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }
}
