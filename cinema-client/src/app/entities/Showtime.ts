import {Time} from '@angular/common';
import {Movie} from './Movie';
import {Screen} from './Screen';

export class Showtime {
  id: number;
  date: Date;
  time: Date;
  technology: Technology;
  movie: Movie;
  screen: Screen;

  constructor() {
  }
}

export enum Technology {
  tec_2D, tec_3D, text_4DX
}

