import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../service';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm;
  submitted = false;
  loginMsg: string;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  login() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.authService.authenticate(this.f.email.value, this.f.password.value)
      .subscribe((res) => {
        this.loginMsg = null;
        let decoded;
        let isAdmin = false;

        console.log('email: ' + this.f.email.value + '\ntoken: ' + localStorage.getItem('token'));

        decoded = jwt_decode(this.authService.getToken());
        isAdmin = decoded.admin;

        if ( isAdmin === false ) {
          this.router.navigate(['userc/']);
        } else if ( isAdmin === true ) {
          this.router.navigate(['adminc/']);
        }
      }, (error) => {
        this.loginMsg = error.statusText;
        window.alert('Your credentials are invalid!');
      });
  }
}
