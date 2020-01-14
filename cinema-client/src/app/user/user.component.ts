import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from './user-service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

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
