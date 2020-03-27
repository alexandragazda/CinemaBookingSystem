import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserService} from '../../user-service';
import {User} from '../../../entities/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-modify-info',
  templateUrl: './modify-info.component.html',
  styleUrls: ['./modify-info.component.css']
})

export class ModifyInfoComponent implements OnInit {

  user = new User();

  personalInfoForm;
  submitted = false;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.personalInfoForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phoneNumber: ['', [Validators.pattern('[0][7][0-9]+'), Validators.minLength(10), Validators.maxLength(10)]],
    });

    this.userService.getUserInfo()
      .subscribe(data => {
        this.user = data;
        this.personalInfoForm = this.formBuilder.group({
          firstName: [this.user.firstName, Validators.required],
          lastName: [this.user.lastName, Validators.required],
          phoneNumber: [this.user.phoneNumber, [Validators.pattern('[0][7][0-9]+'), Validators.minLength(10), Validators.maxLength(10)]],
        });
      });
  }

  get f() {
    return this.personalInfoForm.controls;
  }

  modifyPersonalInfo() {
    this.submitted = true;

    if (this.personalInfoForm.invalid) {
      document.getElementById('result').innerHTML = '';
      return;
    }

    this.userService.modifyUserInfo(this.f.firstName.value, this.f.lastName.value, this.f.phoneNumber.value)
      .subscribe((res) => {
          document.getElementById('result').innerHTML = 'Your profile was updated successfully!';
        }, (error) => {
          document.getElementById('result').innerText = 'An error has occurred!';
          document.getElementById('result').style.color = '#1BA098';
        }
      );
  }

  goBack() {
    this.router.navigate(['/my-account/manage-account']);
  }
}
