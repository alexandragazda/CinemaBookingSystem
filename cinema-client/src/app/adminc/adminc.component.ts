import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/service';

@Component({
  selector: 'app-adminc',
  templateUrl: './adminc.component.html',
  styleUrls: ['./adminc.component.css']
})
export class AdmincComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  send() {
    this.authService.send()
      .subscribe((res) => {

      });
  }
}
