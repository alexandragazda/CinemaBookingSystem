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
    if (this.activeLink === 1) { document.getElementById('1').classList.add('active'); }
    if (this.activeLink === 2) { document.getElementById('2').classList.add('active'); }
    if (this.activeLink === 4) { document.getElementById('4').classList.add('active'); }
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

  history() {
    this.router.navigate(['/my-account/history']);
  }
}
