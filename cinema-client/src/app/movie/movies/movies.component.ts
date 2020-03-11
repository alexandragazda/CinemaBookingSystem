import {Component, OnInit} from '@angular/core';
import {Movie} from '../../entities/Movie';
import {MovieService} from '../movie-service';
import {DatePipe} from '@angular/common';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  private routeSub: Subscription;
  private date: string;

  movies: Movie[];

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  daysArray = new Array<Date>();
  day1 = new Date(2020, 2, 14); // new Date() -today, January is 0
  // day1 = new Date(); // !!!!!! today
  day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();
  dayString = 'day';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private movieService: MovieService,
              private datePipe: DatePipe) {

    // tslint:disable-next-line:only-arrow-functions
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };
  }

  initDates() {
    let i;
    for (i = 1; i < 7; i++) {
      this.daysArray[i].setDate(this.daysArray[i - 1].getDate() + 1);
    }
    for (i = 1; i <= 7; i++) {
      document.getElementById('day' + i).setAttribute('id', this.datePipe.transform(this.daysArray[i - 1], 'yyyy-MM-dd'));
    }
  }

  ngOnInit() {
    this.daysArray.push(this.day1, this.day2, this.day3, this.day4, this.day5, this.day6, this.day7);
    this.initDates();

    this.routeSub = this.route.queryParams.subscribe(params => {
      if (params.date === undefined) {
        this.date = this.datePipe.transform(new Date(2020, 2, 14), 'yyyy-MM-dd'); // !!!!! today
        this.router.navigate(['movies'], {queryParams: {date : this.date}});
      } else {
        this.date = params.date;
      }
      document.getElementById(this.date.toString()).classList.add('current-day');
    });

    return this.movieService.getMoviesByDate(this.date.toString())
      .subscribe(data => {
        this.movies = data;
        this.movies.forEach( x => {
          if (x.releaseDate.toString() === this.date) {
            x.premiere = true;
          } else {
            x.premiere = false;
          }
        });

        if (this.movies.length === 0 && this.date === this.datePipe.transform(this.day1, 'yyyy-MM-dd')) {
          this.router.navigate(['movies'], {queryParams: {date : this.datePipe.transform(this.day2, 'yyyy-MM-dd')}});

          return this.movieService.getMoviesByDate(this.datePipe.transform(this.day2, 'yyyy-MM-dd'))
            .subscribe(data1 => this.movies = data1);
        }
      });
  }

  getMoviesByDate(date: Date) {
    this.router.navigate(['movies'], {queryParams: {date : this.datePipe.transform(date, 'yyyy-MM-dd')}});

    return this.movieService.getMoviesByDate(this.datePipe.transform(date, 'yyyy-MM-dd'))
      .subscribe(data => this.movies = data);
  }
}
