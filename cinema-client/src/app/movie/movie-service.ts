import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Movie} from '../entities/Movie';
import {Showtime} from '../entities/Showtime';
import {MovieDTO} from '../entities/MovieDTO';

const movieURL = 'http://localhost:3000';
const getMoviesByDateURL = `${movieURL}/movies`;
const getShowtimeByMovieIdAndDateURL = `${movieURL}/showtimes`;
const watchlistURL = `${movieURL}/watchlistmovies`;

@Injectable({
  providedIn: 'root'
})

export class MovieService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {}

  getMoviesByDate(date: string) {
  return this.httpClient.get<Movie[]>(getMoviesByDateURL, {headers: this.headers, params: { date}})
      .pipe(tap(response => {
      }));
  }

  getShowtimeByMovieIdAndDate(movieId: string, date: string) {
    return this.httpClient.get<Showtime[]>(getShowtimeByMovieIdAndDateURL, {headers: this.headers, params: {movieId, date}})
      .pipe(tap(response => {
      }));
  }

  addWatchlist(watchlistID: string, movieID: number) {
    return this.httpClient.post(watchlistURL, {watchlistID, movieID}, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }

  checkWatchlistMovieByWatchlistIDAndMovieID(movieID: string) {
    return this.httpClient.get<boolean>(watchlistURL, {headers: this.headers, params: {movieID}})
      .pipe(tap(response => {
      }));
  }

  removeMovieFromWatchlist(movieID: string) {
    return this.httpClient.delete(watchlistURL, {headers: this.headers, params: {movieID}})
      .pipe(tap(response => {

      }));
  }

  comingSoon(month: number) {
    const url = `${movieURL}/comingSoonMovies/${month}`;
    return this.httpClient.get<MovieDTO[]>(url, {headers: this.headers})
      .pipe(tap(response => {
      }));
  }

  getMoviesById(id: string) {
    const url = `${movieURL}/movies/${id}`;
    return this.httpClient.get<Movie>(url, {headers: this.headers})
      .pipe(tap(response => {

      }));
  }
}
