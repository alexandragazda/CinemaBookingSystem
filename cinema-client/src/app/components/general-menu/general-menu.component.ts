import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {ActivatedRoute, Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-general-menu',
  templateUrl: './general-menu.component.html',
  styleUrls: ['./general-menu.component.css'],
})

export class GeneralMenuComponent implements OnInit {

  private date = new Date(2020, 2, 19); // January is 0
  // private date = new Date();

  // tslint:disable-next-line:max-line-length
  constructor(private render: Renderer2, private datePipe: DatePipe, private authService: AuthService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {}

  login() {
    // if (this.authService.getToken() === null) {
    this.router.navigate(['/auth']);
    // } else {
    //   let decoded;
    //   let isAdmin: boolean;
    //   decoded = jwt_decode(this.authService.getToken());
    //   isAdmin = decoded.admin;
    //
    //   if ( isAdmin === false ) {
    //     this.router.navigate(['/my-account']);
    //   } else if ( isAdmin === true ) {
    //     this.router.navigate(['/auth/admin']);
    //   }
    // }
  }

  manageAccount() {
    this.router.navigate(['/my-account/manage-account']);
  }

  playingNow() {
    this.router.navigate(['movies'], {queryParams: {date : this.datePipe.transform(this.date, 'yyyy-MM-dd')}});
  }

  home() {
    this.router.navigate(['']);
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

  history() {
    this.router.navigate(['/my-account/history']);
  }
}
