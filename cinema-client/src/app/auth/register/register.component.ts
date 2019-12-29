import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../service';
import {PasswordValidator} from './validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

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
      phoneNumber: [ '' ],
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
        this.router.navigate(['/successful-registration']);
      }, (error) => {
        window.alert(JSON.parse(JSON.stringify(error)).error);
      });

  }

}
