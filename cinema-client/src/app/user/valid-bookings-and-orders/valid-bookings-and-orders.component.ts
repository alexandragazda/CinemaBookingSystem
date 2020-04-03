import { Component, OnInit } from '@angular/core';
import {BookingInfoDTO} from '../../entities/BookingInfoDTO';
import {OrderInfoDTO} from '../../entities/OrderInfoDTO';
import {UserService} from '../user-service';
import {DatePipe} from '@angular/common';
import {BookingService} from '../../booking/booking-service';
import {Router} from '@angular/router';
import swal from 'sweetalert2';
import {OrderService} from '../../order/order-service';

@Component({
  selector: 'app-valid-bookings-and-orders',
  templateUrl: './valid-bookings-and-orders.component.html',
  styleUrls: ['./valid-bookings-and-orders.component.css']
})
export class ValidBookingsAndOrdersComponent implements OnInit {

  validBookings = new Array<BookingInfoDTO>();
  validOrders = new Array<OrderInfoDTO>();
  showMoreBookingInfoBools = new Array<boolean>();
  showMoreOrderInfoBools = new Array<boolean>();

  constructor(private userService: UserService, private bookingService: BookingService, private orderService: OrderService,
              private datePipe: DatePipe,
              private router: Router) { }

  ngOnInit() {
    this.userService.getUserValidBookings()
      .subscribe(data => {
        this.validBookings = data;
        let i; let ids = '';
        for (i = 0; i < this.validBookings.length; i++) {
          this.showMoreBookingInfoBools.push(false);
          ids += this.validBookings[i].bookingID + ' ';
        }
        // window.alert(ids);
      });

    this.userService.getUserValidOrders()
      .subscribe(data => {
        this.validOrders = data;
        let i; let ids = '';
        for (i = 0; i < this.validOrders.length; i++) {
          this.showMoreOrderInfoBools.push(false);
          ids += this.validOrders[i].orderID + ' ';
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
    const id = (index + this.validBookings.length + 1).toString();
    if (this.showMoreOrderInfoBools[index] === true) {
      document.getElementById(id).innerText = 'Less';
    } else {
      document.getElementById(id).innerText = 'More';
    }
  }

  deleteBooking(bookingID: number, index: number) {
    swal.fire({
      background: '#1B2631',
      title: '<span style="color:#1BA098; font-size: 22px">Are you sure you want to cancel your booking?<span>',
      showCancelButton: true,
      cancelButtonText: 'No',
      cancelButtonColor: '#283747',
      confirmButtonText: 'Yes',
      confirmButtonColor: '#1BA098',
    }).then((result) => {
      if (result.value) {
        let show = true;
        if (index !== this.validBookings.length - 1 && this.showMoreBookingInfoBools[index + 1] === false) {
          show = false;
        }
        this.bookingService.deleteBooking(bookingID.toString())
          .subscribe((res) => {
            this.showMoreBookingInfoBools[index] = show;
            this.validBookings = this.validBookings
              .filter(x => x.bookingID !== bookingID);
          }, (error) => {
            this.router.navigate(['/error'], {queryParams: {code: 5}});
          });
      }
    });
  }

  bookingCanBeCancelled(i: number) {
    const dateString = this.validBookings[i].showtimeDate + 'T' + this.validBookings[i].showtimeTime;
    const date = new Date(dateString);
    date.setTime(date.getTime() - (2 * 60 * 60 * 1000));

    const today = new Date(); // !!!!!!!!!!! today (urmatoarele 2 randuri dispar)
    today.setTime(new Date().getTime());
    today.setFullYear(2020, 2, 19);

    if (date.getDate() === today.getDate() && date.getTime() < today.getTime()) {
      return false;
    }

    return true;
  }

  orderCanBeCancelled(i: number) {
    const dateString = this.validOrders[i].showtimeDate + 'T' + this.validOrders[i].showtimeTime;
    const date = new Date(dateString);
    date.setTime(date.getTime() - (2 * 60 * 60 * 1000));

    const today = new Date(); // !!!!!!!!!!! today (urmatoarele 2 randuri dispar)
    today.setTime(new Date().getTime());
    today.setFullYear(2020, 2, 19);

    if (date.getDate() === today.getDate() && date.getTime() < today.getTime()) {
      return false;
    }

    return true;
  }

  deleteOrder(orderID: number, index: number) {
    swal.fire({
      background: '#1B2631',
      title: '<span style="color:#1BA098; font-size: 22px">Are you sure you want to cancel your order?<span>',
      showCancelButton: true,
      cancelButtonText: 'No',
      cancelButtonColor: '#283747',
      confirmButtonText: 'Yes',
      confirmButtonColor: '#1BA098',
    }).then((result) => {
      if (result.value) {
        let show = true;
        if (index !== this.validOrders.length - 1 && this.showMoreOrderInfoBools[index + 1] === false) {
            show = false;
        }
        this.orderService.deleteOrder(orderID.toString())
          .subscribe((res) => {
            this.showMoreOrderInfoBools[index] = show;
            this.validOrders = this.validOrders
              .filter(x => x.orderID !== orderID);
          }, (error) => {
            this.router.navigate(['/error'], {queryParams: {code: 5}});
          });
      }
    });
  }
}
