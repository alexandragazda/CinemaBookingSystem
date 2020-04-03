import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {MovieDTO} from '../entities/MovieDTO';
import {User} from '../entities/User';
import {BookingInfoDTO} from '../entities/BookingInfoDTO';
import {OrderInfoDTO} from '../entities/OrderInfoDTO';

const URL = 'http://localhost:3000';
const resetPasswordURL = `${URL}/reset-password`;
const userURL = `${URL}/user`;
const watchlistURL = `${URL}/movieswatchlist`;
const validBookingsURL = `${URL}/validBookings`;
const validOrdersURL = `${URL}/validOrders`;
const expiredBookingsURL = `${URL}/expiredBookings`;
const expiredOrdersURL = `${URL}/expiredOrders`;

interface UserResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})

export class UserService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  resetPassword(oldPassword: string, newPassword: string): Observable<UserResponse> {
    return this.httpClient.put<UserResponse>(resetPasswordURL,
      {oldPassword, newPassword}, this.httpOptions)
      .pipe(tap(response => {

      }));
  }

  getMoviesFromWatchlist(watchlistID: string) {
    return this.httpClient.get<MovieDTO[]>(watchlistURL, {headers: this.httpOptions.headers, params: {watchlistID}})
      .pipe(tap(response => {
      }));
  }

  getUserInfo() {
    return this.httpClient.get<User>(userURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {
      }));
  }

  modifyUserInfo(newFirstName: string, newLastName: string, newPhoneNumber: string) {
    return this.httpClient.put(userURL, {newFirstName, newLastName, newPhoneNumber}, this.httpOptions)
      .pipe(tap(response => {
      }));
  }

  deleteAccount() {
    return this.httpClient.delete(userURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {

      }));
  }

  getUserValidBookings() {
    return this.httpClient.get<BookingInfoDTO[]>(validBookingsURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {
      }));
  }

  getUserValidOrders() {
    return this.httpClient.get<OrderInfoDTO[]>(validOrdersURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {
      }));
  }

  getUserExpiredBookings() {
    return this.httpClient.get<BookingInfoDTO[]>(expiredBookingsURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {
      }));
  }

  getUserExpiredOrders() {
    return this.httpClient.get<OrderInfoDTO[]>(expiredOrdersURL, {headers: this.httpOptions.headers})
      .pipe(tap(response => {
      }));
  }
}
