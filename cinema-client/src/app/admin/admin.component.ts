import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/auth-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  send() {
    this.authService.send()
      .subscribe((res) => {

      });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['']);
  }

}
