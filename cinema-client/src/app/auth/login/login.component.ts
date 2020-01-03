import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../auth-service';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm;
  submitted = false;

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

    if (this.loginForm.invalid) {
      return;
    }

    this.authService.authenticate(this.f.email.value, this.f.password.value)
      .subscribe((res) => {
        let decoded;
        let isAdmin = false;

        console.log('email: ' + this.f.email.value + '\ntoken: ' + localStorage.getItem('token'));

        decoded = jwt_decode(this.authService.getToken());
        isAdmin = decoded.admin;

        if ( isAdmin === false ) {
          this.router.navigate(['/user']);
        } else if ( isAdmin === true ) {
          this.router.navigate(['admin/']);
        }
      }, () => {
        document.getElementById('loginError').innerHTML = 'Your credentials are invalid! Please try again!';
      });
  }

  register() {
    this.router.navigate(['register/']);
  }

}
