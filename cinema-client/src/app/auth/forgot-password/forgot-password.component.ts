import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth-service';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm;
  submitted = false;

  constructor(private authService: AuthService , private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.forgotPasswordForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  get f() {
    return this.forgotPasswordForm.controls;
  }

  forgotPassword() {
    this.submitted = true;

    if (this.forgotPasswordForm.invalid) {
      return;
    }

    this.authService.forgotPassword(this.f.email.value)
      .subscribe((res) => {
        document.getElementById('forgotPasswordInfo').innerHTML = 'We\'ve sent you an email with your new password!';
        this.f.email.setValue('');
        this.submitted = false;
    }, (error) => {
        document.getElementById('forgotPasswordInfo').innerHTML = JSON.parse(JSON.stringify(error)).error;
      });

  }
}
