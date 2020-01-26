import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../entities/Movie';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  @Input() movie: Movie;

  ytPlayer: YT.Player;
  playerVars = 'origin: http://localhost:4200';

  constructor() {}

  ngOnInit() {
    this.movie.poster = '';
  }

  savePlayer(player) {
    this.ytPlayer = player;
    this.ytPlayer.setSize(400, 300);
    player.loadVideoById(this.movie.trailer);
    this.ytPlayer.stopVideo();
    // const src = 'http://www.youtube.com/embed/' + this.movie.trailer + '?enablejsapi=1&origin=http://localhost:8000';
    // this.ytPlayer.getIframe().setAttribute('src', src);
  }

  onStateChange(event) {}
}
