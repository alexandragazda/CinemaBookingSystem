import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../user-service';
import {User} from '../../../entities/User';
import swal from 'sweetalert2';

@Component({
  selector: 'app-my-account',
  templateUrl: './manage-account.component.html',
  styleUrls: ['./manage-account.component.css']
})
export class ManageAccountComponent implements OnInit {

  user = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userService.getUserInfo()
      .subscribe(data => {
        this.user = data;
      });
  }

  modifyUserInfo() {
    this.router.navigate(['/my-account/manage-account/modify-info']);
  }

  resetPassword() {
    this.router.navigate(['/my-account/manage-account/reset-password']);
  }

  deleteAccount() {
    swal.fire({
      background: '#1B2631',
      title: '<span style="color:#1BA098; font-size: 22px">Are you sure you want to delete your account?<span>',
      showCancelButton: true,
      cancelButtonText: 'CANCEL',
      cancelButtonColor: '#283747',
      confirmButtonText: 'DELETE',
      confirmButtonColor: '#1BA098',
    }).then((result) => {
      if (result.value) {
        this.userService.deleteAccount()
          .subscribe((res) => {
            localStorage.removeItem('token');
            this.router.navigate(['/auth/login']);
          }, (error) => {
            this.router.navigate(['/error'], {queryParams: {code : 5}});
          });
      }
    });
  }
}
