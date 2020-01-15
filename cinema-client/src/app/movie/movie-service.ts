import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Movie} from '../entities/Movie';

const movieURL = 'http://localhost:3000';

@Injectable({
  providedIn: 'root'
})

export class MovieService {
  headers = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private httpClient: HttpClient) {
  }
}
