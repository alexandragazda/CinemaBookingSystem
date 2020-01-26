import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {ActivatedRoute, Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-general-menu',
  templateUrl: './general-menu.component.html',
  styleUrls: ['./general-menu.component.css']
})
export class GeneralMenuComponent implements OnInit {

  private date = new Date(2020, 0, 17); // January is 0
  private queryDate: Date;

  // private date = new Date(); // today

  constructor(private render: Renderer2, private datePipe: DatePipe, private authService: AuthService, private router: Router, private route: ActivatedRoute) {
    // this.route.params.subscribe(params => {
    //   this.queryDate = params.date;
    // });
    //
    // if (this.queryDate !== undefined) {
    //   this.date = this.queryDate;
    // } else {
    //   this.date = new Date(2020, 0, 13);
    // }
  }

  ngOnInit() {

    // document.getElementById('homeBtn').focus();

    // this.route.params.subscribe(params => {
    //   this.queryDate = params.date;
    // });
    //
    // if (this.queryDate !== undefined) {
    //   this.date = this.queryDate;
    // } else {
    //   this.date = new Date(2020, 0, 13);
    // }
  }

  login() {
    if (this.authService.getToken() === null) {
      this.router.navigate(['/auth']);
    } else {
      let decoded;
      let isAdmin: boolean;
      decoded = jwt_decode(this.authService.getToken());
      isAdmin = decoded.admin;

      if ( isAdmin === false ) {
        this.router.navigate(['/my-account']);
      } else if ( isAdmin === true ) {
        this.router.navigate(['/auth/admin']);
      }
    }
  }

  showtimes() {
    this.router.navigate(['movies'], {queryParams: {date : this.datePipe.transform(this.date, 'yyyy-MM-dd')}});
  }
}
