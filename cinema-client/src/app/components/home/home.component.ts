import { Component, OnInit } from '@angular/core';
import {Movie} from '../../entities/Movie';
import {HomeService} from './home-service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-movie-list',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  movies: Movie[];

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  day1 = new Date(2020, 0, 13); // new Date() -today, January is 0
  // day1 = new Date();
  day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();

  constructor(private movieService: HomeService, private datePipe: DatePipe) { }

  initDates() {
    this.day2.setDate(this.day1.getDate() + 1);
    this.day3.setDate(this.day2.getDate() + 1);
    this.day4.setDate(this.day3.getDate() + 1);
    this.day5.setDate(this.day4.getDate() + 1);
    this.day6.setDate(this.day5.getDate() + 1);
    this.day7.setDate(this.day6.getDate() + 1);
  }

  ngOnInit() {
    this.initDates();

    return this.movieService.getMoviesByDate(this.datePipe.transform(this.day1, 'yyyy-MM-dd'))
      .subscribe(data => this.movies = data);
  }

  getMoviesByDate(date: Date) {
    return this.movieService.getMoviesByDate(this.datePipe.transform(date, 'yyyy-MM-dd'))
      .subscribe(data => this.movies = data);
  }
}
