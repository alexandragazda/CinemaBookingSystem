import {Technology} from './Showtime';
import {Time} from '@angular/common';

export class ShowtimeDTO {
  showtimeID: number;
  showtimeDate: Date;
  showtimeTime: Time;
  showtimeTechnology: Technology;
  screenID: number;
}
