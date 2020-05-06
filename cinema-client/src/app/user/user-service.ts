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
const validBookingsURL = `${URL}/valid-bookings`;
const validOrdersURL = `${URL}/valid-orders`;
const expiredBookingsURL = `${URL}/expired-bookings`;
const expiredOrdersURL = `${URL}/expired-orders`;

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

  constructor(private httpClient: HttpClient) {}

  resetPassword(oldPassword: string, newPassword: string): Observable<UserResponse> {
    return this.httpClient.post<UserResponse>(resetPasswordURL,
      {oldPassword, newPassword}, this.httpOptions)
      .pipe(tap(() => {}));
  }

  getMoviesFromWatchlist(watchlistID: string) {
    return this.httpClient.get<MovieDTO[]>(watchlistURL, {headers: this.httpOptions.headers, params: {watchlistID}})
      .pipe(tap(() => {}));
  }

  getUserInfo() {
    return this.httpClient.get<User>(userURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }

  modifyUserInfo(newFirstName: string, newLastName: string, newPhoneNumber: string) {
    return this.httpClient.put(userURL, {newFirstName, newLastName, newPhoneNumber}, this.httpOptions)
      .pipe(tap(() => {}));
  }

  deleteAccount() {
    return this.httpClient.delete(userURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }

  getUserValidBookings() {
    return this.httpClient.get<BookingInfoDTO[]>(validBookingsURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }

  getUserValidOrders() {
    return this.httpClient.get<OrderInfoDTO[]>(validOrdersURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }

  getUserExpiredBookings() {
    return this.httpClient.get<BookingInfoDTO[]>(expiredBookingsURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }

  getUserExpiredOrders() {
    return this.httpClient.get<OrderInfoDTO[]>(expiredOrdersURL, {headers: this.httpOptions.headers})
      .pipe(tap(() => {}));
  }
}
