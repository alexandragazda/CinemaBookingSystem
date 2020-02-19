import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../auth-service';
import {PasswordValidator} from '../auth-validators';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  @Input() goToTickets: boolean;

  registerForm;
  submitted = false;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.registerForm =  this.formBuilder.group({
      email: [ '' , [Validators.required, Validators.email]],
      password: ['' , [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['' , Validators.required],
      firstName: [ '' , Validators.required],
      lastName: [ '' , Validators.required],
      phoneNumber: [ '', [Validators.pattern('[0][7][0-9]+'), Validators.minLength(10), Validators.maxLength(10)] ],
    }, { validator : PasswordValidator.MatchPassword});
  }

  get f() {
    return this.registerForm.controls;
  }

  register() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.authService.register(
      this.f.email.value, this.f.password.value, this.f.firstName.value, this.f.lastName.value, this.f.phoneNumber.value
    )
      .subscribe((res) => {

        window.alert(this.goToTickets);
        if (this.goToTickets === true) {
          // this.router.navigate(['/booking/tickets']);
          this.router.navigate(['/booking/checkout']);
        } else {
          let decoded;
          let isAdmin = false;

          console.log('email: ' + this.f.email.value + '\ntoken: ' + localStorage.getItem('token'));

          decoded = jwt_decode(this.authService.getToken());
          isAdmin = decoded.admin;

          if (isAdmin === false) {
            this.router.navigate(['/my-account']);
          } else if (isAdmin === true) {
            this.router.navigate(['/auth/admin']);
          }
        }

        // this.router.navigate(['/auth/successful-registration']);
      }, (error) => {
        document.getElementById('registerError').innerHTML = JSON.parse(JSON.stringify(error)).error;
      });

  }

}
