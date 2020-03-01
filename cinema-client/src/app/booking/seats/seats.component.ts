import {Component, OnInit} from '@angular/core';
import {BookingData} from '../../entities/BookingData';
import {BookingService} from '../booking-service';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {AuthService} from '../../auth/auth-service';

@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.css']
})
export class SeatsComponent implements OnInit {

  bookingData: BookingData;
  seats = new Array<Array<number>>();
  // rows = new Array<number>();
  // cols = new Array<number>();
  totalSeats: number;
  nrSelectedSeats = 0;
  selectedSeats = '';
  showParaMax = false;
  showParaMin = false;

  // tslint:disable-next-line:max-line-length
  constructor(private bookingService: BookingService, private authService: AuthService, private datePipe: DatePipe, private router: Router) { }

  ngOnInit() {

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, null, this.bookingData.userInfo);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));

    return this.bookingService.getSeats(this.bookingData.showtimeID.toString())
      .subscribe(data => {
        this.seats = data;

        this.totalSeats = this.bookingData.nrChildTicket +
          this.bookingData.nrStudentTicket +
          this.bookingData.nrAdultTicket +
          this.bookingData.nrRetiredTicket;
        console.log('totalSeats: ' + this.totalSeats);

        // let i: number;
        // for (i = 0; i < this.seats.length; i++) {
        //   this.rows.push(i);
        // }
        // for (i = 0; i < this.seats[0].length; i++) {
        //   this.cols.push(i);
        // }
      });
  }

  clickSeat(row: number, col: number) {
    const id = row + '' + col;
    const el = document.getElementById(id);
    if (!el.classList.contains('button-selected')) {
      if (this.totalSeats !== this.nrSelectedSeats) {
        el.classList.add('button-selected');
        this.nrSelectedSeats++;
        this.showParaMax = false;
        this.showParaMin = false;
      } else {
        this.showParaMax = true;
        if (this.totalSeats === 1) {
          document.getElementById('showParaMax').innerText = 'You have already selected ' + this.totalSeats + ' seat ...';
        } else {
          document.getElementById('showParaMax').innerText = 'You have already selected ' + this.totalSeats + ' seats ...';
        }
        this.showParaMin = false;
      }
    } else {
      el.classList.remove('button-selected');
      this.nrSelectedSeats --;
      this.showParaMax = false;
      this.showParaMin = false;
    }
    console.log('nrSelectedSeats: ' + this.nrSelectedSeats);
  }

  finishBooking() {
    if (this.nrSelectedSeats === this.totalSeats) {
      let i: number;
      let j: number;
      for (i = 0; i < this.seats.length; i++) {
        let row = i + ':';
        for (j = 0; j < this.seats[0].length; j++) {
          const id = i + '' + j;
          const el = document.getElementById(id);
          if (el.classList.contains('button-selected')) {
            row += j + ',';
          }
        }

        if (row.length === 2) {
          row = row.substr(0, row.length - 2);
        } else {
          row = row.substr(0, row.length - 1);
          this.selectedSeats += row + ';';
        }

      }

      this.selectedSeats = this.selectedSeats.substr(0, this.selectedSeats.length - 1);
      console.log('selectedSeats: ' + this.selectedSeats);

      // tslint:disable-next-line:max-line-length
      this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.selectedSeats, this.bookingData.userInfo);
      sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));

      if (this.authService.getToken() !== null) {
        this.router.navigate(['/booking/checkout']);
      } else {
        this.router.navigate(['booking/account']);
      }
    } else {
      this.showParaMin = true;
      if (this.totalSeats === 1) {
        document.getElementById('showParaMin').innerText = 'You have not selected ' + this.totalSeats + ' seat yet ...';
      } else {
        document.getElementById('showParaMin').innerText = 'You have not selected ' + this.totalSeats + ' seats yet ...';
      }
    }
  }
}
