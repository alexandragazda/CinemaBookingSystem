import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {Customer} from '../../../entities/Customer';
import {BookingData} from '../../../entities/BookingData';

@Component({
  selector: 'app-no-registration',
  templateUrl: './no-registration.component.html',
  styleUrls: ['./no-registration.component.css']
})
export class NoRegistrationComponent implements OnInit {

  guestForm;
  submitted = false;
  bookingData: BookingData;

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.guestForm =  this.formBuilder.group({
      email: [ '' , [Validators.required, Validators.email]],
      firstName: [ '' , Validators.required],
      lastName: [ '' , Validators.required],
    });

    this.bookingData = JSON.parse(sessionStorage.getItem('bookingData'));
    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats, null);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));
  }

  get f() {
    return this.guestForm.controls;
  }

  guest() {
    this.submitted = true;

    if (this.guestForm.invalid) {
      return;
    }

    const userInfo = new Customer(this.f.email.value, this.f.firstName.value, this.f.lastName.value);
    // tslint:disable-next-line:max-line-length
    this.bookingData = new BookingData(this.bookingData.showtimeID, this.bookingData.movieTitle, this.bookingData.moviePoster, this.bookingData.technology, this.bookingData.screen, this.bookingData.date, this.bookingData.time, this.bookingData.ageRating, this.bookingData.nrChildTicket, this.bookingData.nrStudentTicket, this.bookingData.nrAdultTicket, this.bookingData.nrRetiredTicket, this.bookingData.totalPrice, this.bookingData.selectedSeats, userInfo);
    sessionStorage.setItem('bookingData', JSON.stringify(this.bookingData));

    this.router.navigate(['/booking/checkout']);
  }
}
