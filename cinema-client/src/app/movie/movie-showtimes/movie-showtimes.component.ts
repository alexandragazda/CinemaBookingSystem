import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from '../movie-service';
import {Movie} from '../../entities/Movie';
import {Showtime, Technology} from '../../entities/Showtime';
import {DatePipe, Time} from '@angular/common';
import {AuthService} from '../../auth/auth-service';
import {BookingData} from '../../entities/BookingData';

@Component({
  selector: 'app-movie-showtime',
  templateUrl: './movie-showtimes.component.html',
  styleUrls: ['./movie-showtimes.component.css']
})
export class MovieShowtimesComponent implements OnInit {

  private date: Date;
  private movieId: string;
  private movieTitle: string;

  movie = new Movie();
  showtimes: Showtime[];
  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  day1 = new Date(2020, 1, 14); // new Date() -today, January is 0
  // day1 = new Date();
  day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();

  // tslint:disable-next-line:max-line-length
  constructor(private route: ActivatedRoute, private movieService: MovieService, private datePipe: DatePipe,  private router: Router, private authService: AuthService) {
  }

  initDates() {
    this.day2.setDate(this.day1.getDate() + 1);
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
    this.initDates();

    this.route.queryParams.subscribe(params => {
      this.date = params.date;
      // this.movieTitle = params.movieTitle;
      // this.movieId = params.movieId;
    });

    this.route.params.subscribe(params => {
      this.movieId = params.id;
      this.movieTitle = params.title;
    })

    console.log('date: ' + this.date);
    console.log('movieId: ' + this.movieId);
    console.log('movieTitle: ' + this.movieTitle);

    return this.movieService.getShowtimeByMovieIdAndDate(this.movieId, this.date.toString())
      .subscribe(data => {
        this.showtimes = data;
        if (this.showtimes.length > 0) {
          this.movie = data[0].movie;
          if (this.movie.poster != null) {
            this.movie.poster = 'data:image/jpeg;base64,' + this.movie.poster;
          } else {
            this.movie.poster = 'assets/img/no-photo.png';
          }

          if (this.movie.releaseDate === this.date) {
            document.getElementById('premiere').style.display = 'inline-block';
          }

          document.getElementById(this.date.toString()).focus();
        } else {
          // this.router.navigate(['movies'], {queryParams: {date : this.datePipe.transform(this.date, 'yyyy-MM-dd')}});
          this.router.navigate(['']);
        }
      });
  }

  getShowtimesByDate(date: Date) {
    const myRoute = '/movies/' + this.movie.title.split(' ').join('') + '/' + this.movie.id;
    const paramDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    this.router.navigate([myRoute], {queryParams: {date: paramDate}});

    // return this.movieService.getShowtimeByMovieIdAndDate(this.movieId, this.datePipe.transform(date, 'yyyy-MM-dd'))
    //   .subscribe(data => {
    //     this.showtimes = data;
    //     if (this.showtimes.length > 0) {
    //       this.movie = data[0].movie;
    //       if (this.movie.poster != null) {
    //         this.movie.poster = 'data:image/jpeg;base64,' + this.movie.poster;
    //       } else {
    //         this.movie.poster = 'assets/img/no-photo.png';
    //       }
    //     } else {
    //       this.router.navigate(['']);
    //     }
    //   });
  }

  booking(id: number, technology: Technology, screen: number, time: Time) {
  // booking(showtime: Showtime) {
    const bookingData = new BookingData(id, this.movieTitle, this.movie.poster, technology, screen, this.date, time, this.movie.ageRating, 0, 0, 0, 0, 0);
  //   const bookingData = new BookingData(showtime,0, 0, 0, 0, 0);
    sessionStorage.setItem('bookingData', JSON.stringify(bookingData));
    if (this.authService.getToken() !== null) {
      this.router.navigate(['/booking/tickets']);
    } else {
      this.router.navigate(['booking/account']);
    }
  }
}
