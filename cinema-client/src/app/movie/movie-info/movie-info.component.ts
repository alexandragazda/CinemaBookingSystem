import { Component, OnInit } from '@angular/core';
import {Movie} from '../../entities/Movie';
import {ActivatedRoute} from '@angular/router';
import {MovieService} from '../movie-service';

@Component({
  selector: 'app-movie-info',
  templateUrl: './movie-info.component.html',
  styleUrls: ['./movie-info.component.css']
})
export class MovieInfoComponent implements OnInit {

  private movieId: string;
  private movieTitle: string;
  movie = new Movie();

  constructor(private route: ActivatedRoute, private movieService: MovieService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.movieId = params.id;
      this.movieTitle = params.title;
    });

    return this.movieService.getMoviesById(this.movieId)
      .subscribe(data => {
        this.movie = data;
      });
  }
}
