import { Component, OnInit } from '@angular/core';
import {MovieDTO} from '../../entities/MovieDTO';
import {MovieService} from '../movie-service';

@Component({
  selector: 'app-most-popular',
  templateUrl: './most-popular.component.html',
  styleUrls: ['./most-popular.component.css']
})
export class MostPopularComponent implements OnInit {

  movieDTOs = new Array<MovieDTO>();

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.movieService.getTopMovies()
      .subscribe(data => {
        this.movieDTOs = data;
      });
  }
}
