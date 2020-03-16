import { Component, OnInit } from '@angular/core';
import {MovieDTO} from '../../entities/MovieDTO';
import {UserService} from '../user-service';
import {AuthService} from '../../auth/auth-service';
import * as jwt_decode from 'jwt-decode';
import {MovieService} from '../../movie/movie-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})

export class WatchlistComponent implements OnInit {

  movieDTOs: MovieDTO[];
  userEmail: string;
  previewUrl: any = 'assets/img/no-photo.png';

  // tslint:disable-next-line:max-line-length
  constructor(private userService: UserService, private authService: AuthService, private movieService: MovieService, private router: Router) { }

  ngOnInit() {
    let decoded;
    decoded = jwt_decode(this.authService.getToken());
    this.userEmail = decoded.sub;

    return this.userService.getMoviesFromWatchlist(this.userEmail)
      .subscribe(data => {
        this.movieDTOs = data;
      });
  }

  removeFromWatchlist(id: number) {
    this.movieService.removeMovieFromWatchlist(this.userEmail, id.toString())
      .subscribe((res) => {
        this.ngOnInit();
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }

  goToMovieShowtime(movieID: number, movieTile: string, firstDate: Date) {
    const myRoute = '/movies/' + movieTile.split(' ').join('-') + '/' + movieID;
    this.router.navigate([myRoute], {queryParams: {date : firstDate}});
  }
}
