import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-order-step',
  templateUrl: './order-step.component.html',
  styleUrls: ['./order-step.component.css']
})
export class OrderStepComponent implements OnInit {

  @Input() step: number;

  constructor() { }

  ngOnInit() {
    if (this.step === 1) { document.getElementById('concessions').style.color = '#1BA098'; }
    if (this.step === 2) { document.getElementById('pickupTime').style.color = '#1BA098'; }
    if (this.step === 3) { document.getElementById('account').style.color = '#1BA098'; }
    if (this.step === 4) { document.getElementById('confirmation').style.color = '#1BA098'; }
    if (this.step === 5) { document.getElementById('done').style.color = '#1BA098'; }
  }

}
