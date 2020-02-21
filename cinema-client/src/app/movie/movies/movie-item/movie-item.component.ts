import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../entities/Movie';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

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

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if (this.movie.poster != null) {
      this.previewUrl = 'data:image/jpeg;base64,' + this.movie.poster;
    }

    this.routeSub = this.route.queryParams.subscribe(params => {
      this.date = params.date;
    });

  }

  goToMovieShowtime() {
    const myRoute = '/movies/' + this.movie.title.split(' ').join('-') + '/' + this.movie.id
    this.router.navigate([myRoute], {queryParams: {date : this.date}});
    // this.router.navigate(['/showtimes'], {queryParams: {movieTitle: this.movie.title, movieId: this.movie.id, date : this.date}});
    // const myRoute = '/movies/' + this.movie.title.split(' ').join('') + '/' + this.movie.id;
    // this.router.navigate([myRoute, {movie: new Movie()}]);
  }
}
