import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Movie} from './Movie';
import {win} from 'ngx-youtube-player';
import {DatePipe} from '@angular/common';

const movieURL = 'http://localhost:3000';
const getMoviesByDateURL = `${movieURL}/movies`;

@Injectable({
  providedIn: 'root'
})

export class MovieService {
  headers = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private httpClient: HttpClient) {
  }

  getMoviesByDate(date: string) {
  return this.httpClient.get<Movie[]>(getMoviesByDateURL, {headers: this.headers, params: { date}})
      .pipe(tap(response => {
      }));
  }
}
