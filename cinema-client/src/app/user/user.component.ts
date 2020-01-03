import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/auth-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  send() {
    this.authService.send()
      .subscribe((res) => {

      });
  }

  resetPassword() {
    this.router.navigate(['reset-password/']);
  }
}
