import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

const adminURL = 'http://localhost:3000';
const sendURL = `${adminURL}/send`;

interface AdminResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})

export class AdminService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  send() {
    return this.httpClient.post(sendURL, this.httpOptions)
      .pipe(tap(response => {
      }));
  }
}
