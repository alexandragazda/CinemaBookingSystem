import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Movie} from '../entities/Movie';
import {Showtime} from '../entities/Showtime';

const movieURL = 'http://localhost:3000';
const getMoviesByDateURL = `${movieURL}/movies`;
// const getMovieByIdURL = `${movieURL}/movies/`;
const getShowtimeByMovieIdAndDateURL = `${movieURL}/showtimes`;
@Injectable({
  providedIn: 'root'
})

export class MovieService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {
  }

  getMoviesByDate(date: string) {
  return this.httpClient.get<Movie[]>(getMoviesByDateURL, {headers: this.headers, params: { date}})
      .pipe(tap(response => {
      }));
  }

  // getMovieById(id: string) {
  //   const myPath = getMovieByIdURL + id;
  //   return this.httpClient.get<Movie>(myPath, {headers: this.headers})
  //     .pipe(tap(response => {
  //     }));
  // }

  getShowtimeByMovieIdAndDate(movieId: string, date: string) {
    return this.httpClient.get<Showtime[]>(getShowtimeByMovieIdAndDateURL, {headers: this.headers, params: {movieId, date}})
      .pipe(tap(response => {
      }));
  }
}
