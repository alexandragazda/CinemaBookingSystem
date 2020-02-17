import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

const authURL = 'http://localhost:3000';
const loginURL = `${authURL}/login`;
const registerURL = `${authURL}/register`;
const forgotPasswordURL = `${authURL}/forgot-password`;

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };


  constructor(private httpClient: HttpClient) {
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  authenticate(email: string, password: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(loginURL, {id: email, password}, this.httpOptions)
      .pipe(tap(response => {
        localStorage.setItem('token', JSON.stringify(response));
      }));
  }

  register(email: string, password: string, firstName: string, lastName: string, phoneNumber: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(registerURL,
      {id: email, password, firstName, lastName, phoneNumber}, this.httpOptions)
      .pipe(tap(response => {
        localStorage.setItem('token', JSON.stringify(response));
      }));
  }

  forgotPassword(email: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(forgotPasswordURL,
      {email}, this.httpOptions)
      .pipe(tap(response => {

      }));
  }

}
