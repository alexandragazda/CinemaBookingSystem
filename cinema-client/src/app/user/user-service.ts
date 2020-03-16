import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Movie} from '../entities/Movie';
import {MovieDTO} from '../entities/MovieDTO';

const userURL = 'http://localhost:3000';
const resetPasswordURL = `${userURL}/reset-password`;
const sendURL = `${userURL}/send`;
const watchlistURL = `${userURL}/movieswatchlist`;

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

  send() {
    return this.httpClient.post(sendURL, this.httpOptions)
      .pipe(tap(response => {
      }));
  }

  getMoviesFromWatchlist(watchlistID: string) {
    return this.httpClient.get<MovieDTO[]>(watchlistURL, {headers: this.httpOptions.headers, params: {watchlistID}})
      .pipe(tap(response => {
      }));
  }
}
