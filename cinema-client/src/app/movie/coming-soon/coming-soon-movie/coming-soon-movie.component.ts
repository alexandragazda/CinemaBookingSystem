import {Component, Input, OnInit} from '@angular/core';
import {MovieDTO} from '../../../entities/MovieDTO';
import {MovieService} from '../../movie-service';
import {AuthService} from '../../../auth/auth-service';
import {Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-coming-soon-movie',
  templateUrl: './coming-soon-movie.component.html',
  styleUrls: ['./coming-soon-movie.component.css']
})
export class ComingSoonMovieComponent implements OnInit {
  @Input() movieDTO: MovieDTO;

  private isInWatchlist = false;

  constructor(private movieService: MovieService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    if (this.authService.getToken() !== null && this.movieDTO.movieID !== undefined) {
      this.movieService.checkWatchlistMovieByWatchlistIDAndMovieID(this.movieDTO.movieID.toString())
        .subscribe(data => {
          this.isInWatchlist = data;
        });
    }
  }

  addToWatchlist() {
    let decoded; let userEmail;
    decoded = jwt_decode(this.authService.getToken());
    userEmail = decoded.sub;

    this.movieService.addWatchlist(userEmail, this.movieDTO.movieID)
      .subscribe((res) => {
        this.isInWatchlist = !this.isInWatchlist;
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }

  removeFromWatchlist() {
    this.movieService.removeMovieFromWatchlist(this.movieDTO.movieID.toString())
      .subscribe((res) => {
        this.isInWatchlist = !this.isInWatchlist;
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }

  goToMovieDetails(movieID: number, movieTitle: string, firstDate: Date) {
    const myRouteShowtimes = '/movies/' + movieTitle.split(' ').join('-') + '/' + movieID;
    const myRouteInfo = '/movies/' + movieTitle.split(' ').join('-') + '/' + movieID + '/info';
    if (firstDate !== null) {
      this.router.navigate([myRouteShowtimes], {queryParams: {date: firstDate}});
    } else {
      this.router.navigate([myRouteInfo]);
    }
  }
}
