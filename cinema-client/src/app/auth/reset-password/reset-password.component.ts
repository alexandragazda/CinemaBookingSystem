import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth-service';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {PasswordValidator} from '../auth-validators';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm;
  submitted = false;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.resetPasswordForm = this.formBuilder.group({
      currentPassword: ['', Validators.required],
      password: ['' , [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['' , Validators.required],
    }, {validator : PasswordValidator.MatchPassword});
  }

  get f() {
    return this.resetPasswordForm.controls;
  }

  resetPassword() {
    this.submitted = true;

    if (this.resetPasswordForm.invalid) {
      return;
    }

    this.authService.resetPassword(this.f.currentPassword.value, this.f.password.value)
     .subscribe((res) => {
       document.getElementById('resetPasswordInfo').innerHTML = 'Your password was changed successfully!';
       this.f.currentPassword.setValue('');
       this.f.password.setValue('');
       this.f.confirmPassword.setValue('');
       this.submitted = false;
     }, (error) => {
       document.getElementById('resetPasswordInfo').innerText = 'Your current password is wrong! Please try again!';
     }
   );
  }
}
