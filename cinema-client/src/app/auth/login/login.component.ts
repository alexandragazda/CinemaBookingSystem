import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from './service';

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
      username: ['', [Validators.required, Validators.email]],
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

    this.authService.authenticate(this.f.username.value, this.f.password.value)
      .subscribe((res) => {
        this.loginMsg = null;
        if ( res.role.id === 'ROLE_USER') {
          this.router.navigate(['userc/']);
        } else if ( res.role.id === 'ROLE_ADMIN') {
          this.router.navigate(['adminc/']);
        }
      }, (error) => {
        this.loginMsg = error.statusText;
        window.alert('error: ' + error.statusText);
      });
  }
}
