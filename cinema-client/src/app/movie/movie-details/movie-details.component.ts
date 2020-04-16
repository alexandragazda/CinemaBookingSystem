import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {Movie} from '../../entities/Movie';
import {AuthService} from '../../auth/auth-service';
import {MovieService} from '../movie-service';
import * as jwt_decode from 'jwt-decode';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit, OnChanges {
  @Input() movie: Movie;

  private mysrc = '';
  private isInWatchlist = false;

  constructor(private movieService: MovieService, private authService: AuthService, private router: Router, private datePipe: DatePipe) {}

  ngOnInit() {
    this.movie.poster = '';
  }

  ngOnChanges() {
    if (this.authService.getToken() !== null && this.movie.id !== undefined) {
      this.movieService.checkWatchlistMovieByWatchlistIDAndMovieID(this.movie.id.toString())
        .subscribe(data => {
          this.isInWatchlist = data;
        });
    }

    this.mysrc = 'http://www.youtube.com/embed/' + this.movie.trailer + '?enablejsapi=1&origin=http://localhost:4200';
    (document.getElementById('player') as HTMLIFrameElement).contentWindow.location.replace(this.mysrc);
  }


  addToWatchlist() {
    let decoded; let userEmail;
    decoded = jwt_decode(this.authService.getToken());
    userEmail = decoded.sub;

    this.movieService.addWatchlist(userEmail, this.movie.id)
      .subscribe(() => {
        this.isInWatchlist = !this.isInWatchlist;
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }

  removeFromWatchlist() {
    this.movieService.removeMovieFromWatchlist(this.movie.id.toString())
      .subscribe(() => {
        this.isInWatchlist = !this.isInWatchlist;
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }
}
