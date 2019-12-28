import { Component, OnInit } from '@angular/core';
import {AuthService} from '../service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  // forgotPassword() {
  //   this.authService.forgotPassword();
  // }
}
