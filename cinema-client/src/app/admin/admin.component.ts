import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AdminService} from './admin-service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit() {
  }

  send() {
    this.adminService.send()
      .subscribe((res) => {

      });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['']);
  }

}
