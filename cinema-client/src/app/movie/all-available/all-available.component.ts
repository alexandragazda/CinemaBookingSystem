import { Component, OnInit } from '@angular/core';
import {MovieDTO} from '../../entities/MovieDTO';
import {MovieService} from '../movie-service';

@Component({
  selector: 'app-all-available',
  templateUrl: './all-available.component.html',
  styleUrls: ['./all-available.component.css'],
})

export class AllAvailableComponent implements OnInit {

  movieDTOs = new Array<MovieDTO>();
  searchText;

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.movieService.getAvailableMovies()
      .subscribe(data => {
        this.movieDTOs = data;
      });
  }
}
