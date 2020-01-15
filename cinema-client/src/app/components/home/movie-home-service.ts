import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Movie} from '../../entities/Movie';

const movieURL = 'http://localhost:3000';
const getMoviesByDateURL = `${movieURL}/movies`;

@Injectable({
  providedIn: 'root'
})

export class MovieHomeService {
  headers = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private httpClient: HttpClient) {
  }

  getMoviesByDate(date: string) {
  return this.httpClient.get<Movie[]>(getMoviesByDateURL, {headers: this.headers, params: { date}})
      .pipe(tap(response => {
      }));
  }
}
