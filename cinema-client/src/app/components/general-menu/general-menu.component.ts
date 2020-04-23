import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {ActivatedRoute, Router} from '@angular/router';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-general-menu',
  templateUrl: './general-menu.component.html',
  styleUrls: ['./general-menu.component.css'],
})

export class GeneralMenuComponent implements OnInit {

  // private date = new Date(2020, 2, 19); // January is 0
  private date = new Date();

  constructor(private render: Renderer2, private datePipe: DatePipe, private authService: AuthService, private router: Router) {
  }

  ngOnInit() {}

  login() {
    this.router.navigate(['/auth']);
  }

  manageAccount() {
    this.router.navigate(['/my-account/manage-account']);
  }

  playingNow() {
    this.router.navigate(['movies/now'], {queryParams: {date : this.datePipe.transform(this.date, 'yyyy-MM-dd')}});
  }

  home() {
    this.router.navigate(['movies/all']);
  }

  logout() {
    localStorage.removeItem('token');

    if (this.router.url.includes('my-account')) {
      this.router.navigate(['/auth/login']);
    }
  }

  watchlist() {
    this.router.navigate(['/my-account/watchlist']);
  }

  valid() {
    this.router.navigate(['/my-account/valid-bookings-and-orders']);
  }

  history() {
    this.router.navigate(['/my-account/history']);
  }

  comingSoon() {
    this.router.navigate(['/movies/coming-soon'], {queryParams: {month : 'all'}});
  }

  mostPopular() {
    this.router.navigate(['/movies/most-popular']);
  }
}
