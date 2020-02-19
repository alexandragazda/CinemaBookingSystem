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
  day1 = new Date(2020, 1, 14); // new Date() -today, January is 0
  // day1 = new Date();
  day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();

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
    // window.alert(this.day1.getDate()+1);
    this.day2.setDate(this.day1.getDate() + 1);
    // this.day2.setFullYear(2020, 0, 18);
    this.day3.setDate(this.day2.getDate() + 1);
    this.day4.setDate(this.day3.getDate() + 1);
    this.day5.setDate(this.day4.getDate() + 1);
    this.day6.setDate(this.day5.getDate() + 1);
    this.day7.setDate(this.day6.getDate() + 1);

    document.getElementById('day1').setAttribute('id', this.datePipe.transform(this.day1, 'yyyy-MM-dd'));
    document.getElementById('day2').setAttribute('id', this.datePipe.transform(this.day2, 'yyyy-MM-dd'));
    document.getElementById('day3').setAttribute('id', this.datePipe.transform(this.day3, 'yyyy-MM-dd'));
    document.getElementById('day4').setAttribute('id', this.datePipe.transform(this.day4, 'yyyy-MM-dd'));
    document.getElementById('day5').setAttribute('id', this.datePipe.transform(this.day5, 'yyyy-MM-dd'));
    document.getElementById('day6').setAttribute('id', this.datePipe.transform(this.day6, 'yyyy-MM-dd'));
    document.getElementById('day7').setAttribute('id', this.datePipe.transform(this.day7, 'yyyy-MM-dd'));

  }

  ngOnInit() {
    document.getElementById('day1').focus();

    this.initDates();

    this.routeSub = this.route.queryParams.subscribe(params => {
      if (params.date === undefined) {
        this.date = this.datePipe.transform(new Date(2020, 1, 14), 'yyyy-MM-dd'); // !!!!! today
        this.router.navigate(['movies'], {queryParams: {date : this.date}});
      } else {
        this.date = params.date;
      }
      document.getElementById(this.date.toString()).focus();
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
        })

        if (this.movies.length === 0 ) {
          this.router.navigate(['/error'], {queryParams: {code: 1}});

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
