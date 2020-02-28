import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../entities/Movie';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  @Input() movie: Movie;

  // ytPlayer: YT.Player;
  // playerVars = 'origin: http://localhost:4200';
  mysrc = '';

  constructor() {}

  ngOnInit() {
    this.movie.poster = '';
  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnChanges() {
    this.mysrc = 'http://www.youtube.com/embed/' + this.movie.trailer + '?enablejsapi=1&origin=http://localhost:4200';
    (document.getElementById('player') as HTMLIFrameElement).contentWindow.location.replace(this.mysrc);
  }


  // savePlayer(player) {
  //   this.ytPlayer = player;
  //   player.loadVideoById(this.movie.trailer);
  //   this.ytPlayer.stopVideo();
  // }
  //
  // onStateChange(event) {}
}
