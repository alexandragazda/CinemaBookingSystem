import { Component, OnInit } from '@angular/core';
import {Movie} from '../Movie';
import {MovieService} from '../movie-service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: Movie[];

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  day1 = new Date(2020, 0, 13); // new Date() -today, January is 0
  // day1 = new Date();
  day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();
  dayName2: string; dayName3: string; dayName4: string; dayName5: string; dayName6: string; dayName7: string;
  day1Format: string; day2Format: string; day3Format: string; day4Format: string; day5Format: string; day6Format: string;
  day7Format: string;

  constructor(private movieService: MovieService, private datePipe: DatePipe) { }

  initDates() {
    this.day2.setDate(this.day1.getDate() + 1);
    this.day3.setDate(this.day2.getDate() + 1);
    this.day4.setDate(this.day3.getDate() + 1);
    this.day5.setDate(this.day4.getDate() + 1);
    this.day6.setDate(this.day5.getDate() + 1);
    this.day7.setDate(this.day6.getDate() + 1);

    this.day1Format = this.datePipe.transform(this.day1, 'yyyy-MM-dd');
    this.day2Format = this.datePipe.transform(this.day2, 'yyyy-MM-dd');
    this.day3Format = this.datePipe.transform(this.day3, 'yyyy-MM-dd');
    this.day4Format = this.datePipe.transform(this.day4, 'yyyy-MM-dd');
    this.day5Format = this.datePipe.transform(this.day5, 'yyyy-MM-dd');
    this.day6Format = this.datePipe.transform(this.day6, 'yyyy-MM-dd');
    this.day7Format = this.datePipe.transform(this.day7, 'yyyy-MM-dd');

    this.dayName2 = this.days[this.day2.getDay()];
    this.dayName3 = this.days[this.day3.getDay()];
    this.dayName4 = this.days[this.day4.getDay()];
    this.dayName5 = this.days[this.day5.getDay()];
    this.dayName6 = this.days[this.day6.getDay()];
    this.dayName7 = this.days[this.day7.getDay()];

  }
  ngOnInit() {
    this.initDates();

    return this.movieService.getMoviesByDate(this.day1Format)
      .subscribe(data => this.movies = data);
  }

  getMoviesByDate(date: string) {
    return this.movieService.getMoviesByDate(date)
      .subscribe(data => this.movies = data);
  }
}
