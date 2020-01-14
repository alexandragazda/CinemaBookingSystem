import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../Movie';

@Component({
  selector: 'app-movie-item',
  templateUrl: './movie-item.component.html',
  styleUrls: ['./movie-item.component.css']
})
export class MovieItemComponent implements OnInit {

  @Input() movie: Movie;

  private previewUrl: any = 'assets/img/no-photo.png';

  constructor() { }

  ngOnInit() {
    if (this.movie.poster != null) {
      this.previewUrl = 'data:image/jpeg;base64,' + this.movie.poster;
    }
  }

}
