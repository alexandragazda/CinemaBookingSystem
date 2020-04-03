import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from '../movie-service';
import {Movie} from '../../entities/Movie';
import {Showtime, Technology} from '../../entities/Showtime';
import {DatePipe, Time} from '@angular/common';
import {AuthService} from '../../auth/auth-service';
import {BookingData} from '../../entities/BookingData';
import {OrderData} from '../../entities/OrderData';

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
  daysArray = new Array<Date>();
  day1 = new Date(2020, 2, 19); // new Date() -today, January is 0
  // day1 = new Date(); // !!!!!!today

  // day2 =  new Date(); day3 = new Date(); day4 = new Date(); day5 = new Date(); day6 = new Date(); day7 = new Date();

  // tslint:disable-next-line:max-line-length
  constructor(private route: ActivatedRoute, private movieService: MovieService, private datePipe: DatePipe,  private router: Router, private authService: AuthService) {
  }

  initDates() {
    this.daysArray.push(this.day1);

    let i;
    for (i = 1; i < 7; i++) {
      this.daysArray.push(new Date(this.daysArray[i - 1]));
      this.daysArray[i].setDate(this.daysArray[i].getDate() + 1);
    }

    // this.day2 = new Date(this.day1); this.day2.setDate(this.day2.getDate() + 1); this.daysArray.push(this.day2);
    // this.day3 = new Date(this.day2); this.day3.setDate(this.day3.getDate() + 1); this.daysArray.push(this.day3);
    // this.day4 = new Date(this.day3); this.day4.setDate(this.day4.getDate() + 1); this.daysArray.push(this.day4);
    // this.day5 = new Date(this.day4); this.day5.setDate(this.day5.getDate() + 1); this.daysArray.push(this.day5);
    // this.day6 = new Date(this.day5); this.day6.setDate(this.day6.getDate() + 1); this.daysArray.push(this.day6);
    // this.day7 = new Date(this.day6); this.day7.setDate(this.day7.getDate() + 1); this.daysArray.push(this.day7);

    for (i = 1; i <= 7; i++) {
      document.getElementById('day' + i).setAttribute('id', this.datePipe.transform(this.daysArray[i - 1], 'yyyy-MM-dd'));
    }
  }

  ngOnInit() {
    // this.daysArray.push(this.day1, this.day2, this.day3, this.day4, this.day5, this.day6, this.day7);
    // this.daysArray.push(this.day1);
    this.initDates();

    this.route.queryParams.subscribe(params => {
      this.date = params.date;
    });

    this.route.params.subscribe(params => {
      this.movieId = params.id;
      this.movieTitle = params.title;
    });

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
          document.getElementById(this.date.toString()).classList.add('current-day');
        } else {
          this.router.navigate(['/error'], {queryParams: {code: 1}});
        }
      });
  }

  getShowtimesByDate(date: Date) {
    const myRoute = '/movies/' + this.movie.title.split(' ').join('-') + '/' + this.movie.id;
    const paramDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    this.router.navigate([myRoute], {queryParams: {date: paramDate}});
  }

  booking(id: number, technology: Technology, screen: number, time: Time) {
    // tslint:disable-next-line:max-line-length
    const bookingData = new BookingData(id, this.movieTitle.split('-').join(' '), this.movie.poster, technology, screen, this.date, time, this.movie.ageRating, 0, 0, 0, 0, 0, null, null);
    sessionStorage.setItem('bookingData', JSON.stringify(bookingData));
    // tslint:disable-next-line:max-line-length
    const orderData = new OrderData(id, this.date, time, technology, screen, this.movieTitle.split('-').join(' '), this.movie.ageRating, null, 0, null, null);
    sessionStorage.setItem('orderData', JSON.stringify(orderData));
    this.router.navigate(['/select-booking-order']);
  }
}
