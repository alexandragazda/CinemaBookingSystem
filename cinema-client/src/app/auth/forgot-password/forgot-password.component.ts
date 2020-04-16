import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth-service';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm;
  submitted = false;

  // tslint:disable-next-line:max-line-length
  constructor(private authService: AuthService , private router: Router, private formBuilder: FormBuilder, private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.spinnerService.hide();

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

    this.spinnerService.show();

    this.authService.forgotPassword(this.f.email.value)
      .subscribe((res) => {
        document.getElementById('forgotPasswordInfo').innerHTML = 'Check your email!';
        document.getElementById('forgotPasswordInfo').style.color = '#1BA098';
        this.f.email.setValue('');
        this.submitted = false;
    }, (error) => {
        document.getElementById('forgotPasswordInfo').innerHTML = JSON.parse(JSON.stringify(error)).error;
        document.getElementById('forgotPasswordInfo').style.color = '#1BA098';
      });

  }
}
