import { Component, OnInit } from '@angular/core';
import {OrderService} from '../order-service';
import {Concession} from '../../entities/Concession';
import {OrderData, OrderItemDetails} from '../../entities/OrderData';
import {Router} from '@angular/router';

@Component({
  selector: 'app-concessions',
  templateUrl: './concessions.component.html',
  styleUrls: ['./concessions.component.css']
})
export class ConcessionsComponent implements OnInit {

  concessions: Concession[];
  // food = new Array<Concession>();
  popcorn = new Array<Concession>(); nachos = new Array<Concession>(); other = new Array<Concession>();
  drink = new Array<Concession>();
  // menus = new Array<Concession>();
  popcornMenus = new Array<Concession>(); nachosMenus = new Array<Concession>(); otherMenus = new Array<Concession>();
  orderData: OrderData;
  selectItemsPara: boolean;

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit() {
    this.orderData = JSON.parse(sessionStorage.getItem('orderData'));
    // tslint:disable-next-line:max-line-length
    this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime,  this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, null, 0, null, this.orderData.userInfo);
    sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

    this.selectItemsPara = false;

    return this.orderService.getConcessions()
      .subscribe(data => {
        this.concessions = data;
        data.forEach(x => {
          if (x.concessionType.id === 'Food') {
            // this.food.push(x);
            if (x.name.includes('Popcorn')) {
              this.popcorn.push(x);
            } else if (x.name.includes('Nachos')) {
              this.nachos.push(x);
            } else {
              this.other.push(x);
            }
          } else if (x.concessionType.id === 'Drink') {
            this.drink.push(x);
          } else if (x.concessionType.id === 'Menu') {
            // this.menus.push(x);
            if (x.name.includes('Popcorn')) {
              this.popcornMenus.push(x);
            } else if (x.name.includes('Nachos')) {
              this.nachosMenus.push(x);
            } else {
              this.otherMenus.push(x);
            }
          }
        });
      });
  }

  nextStep() {
    const returnedList = new Array<OrderItemDetails>();

    this.concessions.forEach(x => {
      const value = document.getElementById(x.id.toString()).getAttribute('value');
      if (Number(value) !== 0) {
        const orderItem = new OrderItemDetails(x.id, x.name, x.description, x.price, Number(value));
        returnedList.push(orderItem);
      }
    });

    if (returnedList.length === 0) {
      this.selectItemsPara = true;
      document.getElementById('selectItemsPara').style.visibility = 'visible';
    } else {
      let totalPrice = 0;
      returnedList.forEach(x => {
        totalPrice += x.concessionPrice * x.qty;
      });

      // tslint:disable-next-line:max-line-length
      this.orderData = new OrderData(this.orderData.showtimeID, this.orderData.showtimeDate, this.orderData.showtimeTime, this.orderData.showtimeTechnology, this.orderData.showtimeScreen, this.orderData.movieTitle, this.orderData.ageRating, returnedList, totalPrice, null, this.orderData.userInfo);
      sessionStorage.setItem('orderData', JSON.stringify(this.orderData));

      this.router.navigate(['order/pick-up-time']);
    }
  }
}
