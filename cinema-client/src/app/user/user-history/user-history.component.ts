import { Component, OnInit } from '@angular/core';
import {BookingInfoDTO} from '../../entities/BookingInfoDTO';
import {UserService} from '../user-service';
import {DatePipe} from '@angular/common';
import {OrderInfoDTO} from '../../entities/OrderInfoDTO';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit {

  expiredBookings = new Array<BookingInfoDTO>();
  expiredOrders = new Array<OrderInfoDTO>();
  showMoreBookingInfoBools = new Array<boolean>();
  showMoreOrderInfoBools = new Array<boolean>();

  constructor(private userService: UserService, private datePipe: DatePipe) { }

  ngOnInit() {
    this.userService.getUserExpiredBookings()
      .subscribe(data => {
        this.expiredBookings = data;
        let i; let ids = '';
        for (i = 0; i < this.expiredBookings.length; i++) {
          this.showMoreBookingInfoBools.push(false);
          ids += this.expiredBookings[i].bookingID + ' ';
        }
        // window.alert(ids);
      });

    this.userService.getUserExpiredOrders()
      .subscribe(data => {
        this.expiredOrders = data;
        let i; let ids = '';
        for (i = 0; i < this.expiredOrders.length; i++) {
          this.showMoreOrderInfoBools.push(false);
          ids += this.expiredOrders[i].orderID + ' ';
        }
        // window.alert(ids);
      });
  }

  getSeatArray(seats: string) {
    let i; let j; const returnedSeats = new Array();

    const parsedSeats = seats.split(';');
    for (i = 0; i < parsedSeats.length; i++) {
      let result = '';
      const rowcols = parsedSeats[i].split(':');
      result += String(Number(rowcols[0]) + 1) + ':';
      const cols =  rowcols[1].split(',');
      for (j = 0; j < cols.length; j++) {
        result += Number(cols[j]) + 1 + ',';
      }

      result = result.substr(0, result.length - 1);
      returnedSeats.push(result);
    }

    return returnedSeats;
  }

  showAdditionalBookingInfo(index: number) {
    this.showMoreBookingInfoBools[index] = !this.showMoreBookingInfoBools[index];
    const id = index.toString();
    if (this.showMoreBookingInfoBools[index] === true) {
      document.getElementById(id).innerText = 'Less';
    } else {
      document.getElementById(id).innerText = 'More';
    }
  }

  showAdditionalOrderInfo(index: number) {
    this.showMoreOrderInfoBools[index] = !this.showMoreOrderInfoBools[index];
    const id = (index + 6).toString();
    if (this.showMoreOrderInfoBools[index] === true) {
      document.getElementById(id).innerText = 'Less';
    } else {
      document.getElementById(id).innerText = 'More';
    }
  }
}
