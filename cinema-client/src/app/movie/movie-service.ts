import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Movie} from '../entities/Movie';
import {MovieDTO} from '../entities/MovieDTO';
import {ShowtimeDTOS} from '../entities/ShowtimeDTOS';

const movieURL = 'http://localhost:3000';
const getMoviesByDateURL = `${movieURL}/movies`;
const getShowtimeByMovieIdAndDateURL = `${movieURL}/showtimes`;
const watchlistURL = `${movieURL}/watchlistmovies`;
const getAvailableMoviesURL = `${movieURL}/available-movies`;
const getTopMoviesURL = `${movieURL}/top-movies`;

@Injectable({
  providedIn: 'root'
})

export class MovieService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) {}

  getMoviesByDate(date: string) {
  return this.httpClient.get<Movie[]>(getMoviesByDateURL, {headers: this.headers, params: { date}})
      .pipe(tap(() => {}));
  }

  getShowtimeByMovieIdAndDate(movieId: string, date: string) {
    return this.httpClient.get<ShowtimeDTOS>(getShowtimeByMovieIdAndDateURL, {headers: this.headers, params: {movieId, date}})
      .pipe(tap(() => {}));
  }

  addWatchlist(watchlistID: string, movieID: number) {
    return this.httpClient.post(watchlistURL, {watchlistID, movieID}, {headers: this.headers})
      .pipe(tap(() => {}));
  }

  checkWatchlistMovieByWatchlistIDAndMovieID(movieID: string) {
    return this.httpClient.get<boolean>(watchlistURL, {headers: this.headers, params: {movieID}})
      .pipe(tap(() => {}));
  }

  removeMovieFromWatchlist(movieID: string) {
    return this.httpClient.delete(watchlistURL, {headers: this.headers, params: {movieID}})
      .pipe(tap(() => {}));
  }

  comingSoon(month: number) {
    const url = `${movieURL}/coming-soon-movies/${month}`;
    return this.httpClient.get<MovieDTO[]>(url, {headers: this.headers})
      .pipe(tap(() => {}));
  }

  getMoviesById(id: string) {
    const url = `${movieURL}/movies/${id}`;
    return this.httpClient.get<Movie>(url, {headers: this.headers})
      .pipe(tap(() => {}));
  }

  getAvailableMovies() {
    return this.httpClient.get<MovieDTO[]>(getAvailableMoviesURL, {headers: this.headers})
      .pipe(tap(() => {}));
  }

  getTopMovies() {
    return this.httpClient.get<MovieDTO[]>(getTopMoviesURL, {headers: this.headers})
      .pipe(tap(() => {}));
  }
}
