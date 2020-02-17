import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ask-login',
  templateUrl: './ask-login.component.html',
  styleUrls: ['./ask-login.component.css']
})
export class AskLoginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  tickets() {
    this.router.navigate(['/booking/tickets']);
  }
}
