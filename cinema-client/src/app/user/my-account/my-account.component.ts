import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../user-service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  send() {
    this.userService.send()
      .subscribe((res) => {

      });
  }

  resetPassword() {
    this.router.navigate(['reset-password']);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['']);
  }

}
