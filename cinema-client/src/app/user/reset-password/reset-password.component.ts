import {Component, OnInit} from '@angular/core';
import {UserService} from '../user-service';
import {Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';
import {PasswordValidator} from '../../auth/auth-validators';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm;
  submitted = false;

  constructor(private userService: UserService, private router: Router, private formBuilder: FormBuilder) { }

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

    this.userService.resetPassword(this.f.currentPassword.value, this.f.password.value)
     .subscribe((res) => {
       document.getElementById('resetPasswordInfo').innerHTML = 'Your password was changed successfully!';
       document.getElementById('resetPasswordInfo').style.color = '#17202A';
       this.f.currentPassword.setValue('');
       this.f.password.setValue('');
       this.f.confirmPassword.setValue('');
       this.submitted = false;
     }, (error) => {
       document.getElementById('resetPasswordInfo').innerText = 'Your current password is wrong! Please try again!';
       document.getElementById('resetPasswordInfo').style.color = '#C0392B';
     }
   );
  }
}
