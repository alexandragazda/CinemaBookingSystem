import {PlacedOrderItemDTO} from './PlacedOrderItemDTO';
import {Time} from '@angular/common';
import {Technology} from './Showtime';

export class OrderInfoDTO {
  orderID: number;
  placedOrderItemDTOS = new Array<PlacedOrderItemDTO>();
  totalPrice: number;
  pickupTime: Time;
  movieTitle: string;
  movieTechnology: Technology;
  showtimeDate: Date;
  showtimeTime: Time;
  screenID: number;
}
