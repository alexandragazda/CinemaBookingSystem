import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-userc',
  templateUrl: './userc.component.html',
  styleUrls: ['./userc.component.css']
})
export class UsercComponent implements OnInit {

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
