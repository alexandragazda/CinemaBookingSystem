import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MovieService} from '../movie-service';
import {Movie} from '../../entities/Movie';
import {Showtime} from '../../entities/Showtime';
import {DatePipe} from '@angular/common';

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

  constructor(private route: ActivatedRoute, private movieService: MovieService, private datePipe: DatePipe) {
  }

  ngOnInit() {
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
        this.movie = data[0].movie;
        if (this.movie.poster != null) {
          this.movie.poster = 'data:image/jpeg;base64,' + this.movie.poster;
        } else {
          this.movie.poster = 'assets/img/no-photo.png';
        }
      });
  }

}
