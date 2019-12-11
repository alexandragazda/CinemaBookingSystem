import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {User} from '../../entities/User';
import {Role} from '../../entities/Role';

const authURL = 'http://localhost:3000';
const loginURL = `${authURL}/login`;

interface AuthResponse {
  token: string;
  role: Role;
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

  authenticate(username: string, password: string): Observable<AuthResponse> {
    const user = new User(username, password);

    window.alert(username + ' ' + password);
    return this.httpClient.post<AuthResponse>(loginURL, {id: username, password: password}, this.httpOptions)
      .pipe(tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role.id);
      }));
  }
}
