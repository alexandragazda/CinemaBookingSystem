import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../entities/Movie';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {AuthService} from '../../../auth/auth-service';
import * as jwt_decode from 'jwt-decode';
import {MovieService} from '../../movie-service';

@Component({
  selector: 'app-movie-item',
  templateUrl: './movie-item.component.html',
  styleUrls: ['./movie-item.component.css']
})
export class MovieItemComponent implements OnInit {

  @Input() movie: Movie;

  private routeSub: Subscription;
  private date;
  private previewUrl: any = 'assets/img/no-photo.png';
  private isInWatchlist = false;

  // tslint:disable-next-line:max-line-length
  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.getToken() !== null) {
      this.movieService.checkWatchlistMovieByWatchlistIDAndMovieID(this.movie.id.toString())
        .subscribe(data => {
          this.isInWatchlist = data;
        });
    }

    if (this.movie.poster != null) {
      this.previewUrl = 'data:image/jpeg;base64,' + this.movie.poster;
    }

    this.routeSub = this.route.queryParams.subscribe(params => {
      this.date = params.date;
    });

  }

  goToMovieShowtime() {
    const myRoute = '/movies/' + this.movie.title.split(' ').join('-') + '/' + this.movie.id;
    this.router.navigate([myRoute], {queryParams: {date : this.date}});
  }

  addToWatchlist() {
    let decoded; let userEmail;
    decoded = jwt_decode(this.authService.getToken());
    userEmail = decoded.sub;

    this.movieService.addWatchlist(userEmail, this.movie.id)
        .subscribe((res) => {
          this.isInWatchlist = !this.isInWatchlist;
        }, (error) => {
          this.router.navigate(['/error'], {queryParams: {code : 5}});
        });
    }

  removeFromWatchlist() {
    this.movieService.removeMovieFromWatchlist(this.movie.id.toString())
      .subscribe((res) => {
        this.isInWatchlist = !this.isInWatchlist;
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }
}
