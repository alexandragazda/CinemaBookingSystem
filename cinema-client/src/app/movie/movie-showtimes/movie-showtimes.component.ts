import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from '../movie-service';
import {Movie} from '../../entities/Movie';
import {Technology} from '../../entities/Showtime';
import {DatePipe, Time} from '@angular/common';
import {BookingData} from '../../entities/BookingData';
import {OrderData} from '../../entities/OrderData';
import {ShowtimeDTO} from '../../entities/ShowtimeDTO';

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
  showtimes = new Array<ShowtimeDTO>();

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  daysArray = new Array<Date>();
  day1 = new Date();

  constructor(private route: ActivatedRoute, private movieService: MovieService, private datePipe: DatePipe,  private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  initDates() {
    this.daysArray.push(this.day1);

    let i;
    for (i = 1; i < 7; i++) {
      this.daysArray.push(new Date(this.daysArray[i - 1]));
      this.daysArray[i].setDate(this.daysArray[i].getDate() + 1);
    }

    for (i = 1; i <= 7; i++) {
      document.getElementById('day' + i).setAttribute('id', this.datePipe.transform(this.daysArray[i - 1], 'yyyy-MM-dd'));
    }
  }

  ngOnInit() {
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

    this.initDates();

    return this.movieService.getShowtimeByMovieIdAndDate(this.movieId, this.date.toString())
      .subscribe(data => {
        this.showtimes = data.showtimeDTOList;
        this.movie = data.movie;
        if (this.showtimes.length > 0) {
          if (this.movie.poster != null) {
            this.movie.poster = 'data:image/jpeg;base64,' + this.movie.poster;
          } else {
            this.movie.poster = 'assets/img/no-photo.png';
          }

          if (this.movie.releaseDate === this.date) {
            document.getElementById('premiere').style.display = 'inline-block';
          }
        }
        document.getElementById(this.date.toString()).classList.add('current-day');
      });
  }

  getShowtimesByDate(date: Date) {
    const myRoute = '/movies/' + this.movie.title.split(' ').join('-') + '/' + this.movie.id;
    const paramDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    this.router.navigate([myRoute], {queryParams: {date: paramDate}});
  }

  nextStep(id: number, technology: Technology, screen: number, time: Time) {
    // tslint:disable-next-line:max-line-length
    const bookingData = new BookingData(id, this.movieTitle.split('-').join(' '), this.movie.poster, technology, screen, this.date, time, this.movie.ageRating, 0, 0, 0, 0, 0, null, null);
    sessionStorage.setItem('bookingData', JSON.stringify(bookingData));

    // tslint:disable-next-line:max-line-length
    const orderData = new OrderData(id, this.date, time, technology, screen, this.movieTitle.split('-').join(' '), this.movie.ageRating, null, 0, null, null);
    sessionStorage.setItem('orderData', JSON.stringify(orderData));

    this.router.navigate(['/select-booking-order']);
  }
}
