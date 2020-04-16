import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../auth-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() goToBookingCheckout: boolean;
  @Input() goToOrderCheckout: boolean;

  loginForm;
  submitted = false;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) {}

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

        if (this.goToBookingCheckout === true) {
          this.router.navigate(['/booking/checkout']);
        } else if (this.goToOrderCheckout === true) {
          this.router.navigate(['/order/checkout']);
        } else {
          console.log('email: ' + this.f.email.value + '\ntoken: ' + localStorage.getItem('token'));

          this.router.navigate(['/my-account']);
        }
      }, () => {
        document.getElementById('loginError').innerHTML = 'Your credentials are invalid...';
      });
  }
}
