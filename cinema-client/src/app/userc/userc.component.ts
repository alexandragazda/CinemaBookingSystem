import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/service';

@Component({
  selector: 'app-userc',
  templateUrl: './userc.component.html',
  styleUrls: ['./userc.component.css']
})
export class UsercComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  send() {
    this.authService.send()
      .subscribe((res) => {

      });
  }
}
