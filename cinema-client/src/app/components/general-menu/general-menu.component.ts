import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {Router} from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-general-menu',
  templateUrl: './general-menu.component.html',
  styleUrls: ['./general-menu.component.css']
})
export class GeneralMenuComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
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
}
