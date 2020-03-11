import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth-service';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderService} from '../order-service';

@Component({
  selector: 'app-successful-order',
  templateUrl: './successful-order.component.html',
  styleUrls: ['./successful-order.component.css']
})
export class SuccessfulOrderComponent implements OnInit {

  code: number;

  // tslint:disable-next-line:max-line-length
  constructor(private orderService: OrderService, private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.code = params.code;
    });

    this.orderService.orderEmail(this.code)
      .subscribe((res) => {
      }, (error) => {
        this.router.navigate(['/error'], {queryParams: {code : 5}});
      });
  }

}
