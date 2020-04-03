import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  @Input() activeLink: number;

  constructor(private router: Router) { }

  ngOnInit() {
    if (this.activeLink === 1) { document.getElementById('manage').classList.add('active'); }
    if (this.activeLink === 2) { document.getElementById('watchlist').classList.add('active'); }
    if (this.activeLink === 3) { document.getElementById('valid').classList.add('active'); }
    if (this.activeLink === 4) { document.getElementById('history').classList.add('active'); }
  }

  watchlist() {
    this.router.navigate(['/my-account/watchlist']);
  }

  manage() {
    this.router.navigate(['/my-account/manage-account']);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/auth/login']);
  }

  valid() {
    this.router.navigate(['/my-account/valid-bookings-and-orders']);
  }

  history() {
    this.router.navigate(['/my-account/history']);
  }
}
