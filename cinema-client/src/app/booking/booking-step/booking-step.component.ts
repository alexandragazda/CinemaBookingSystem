import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-booking-step',
  templateUrl: './booking-step.component.html',
  styleUrls: ['./booking-step.component.css']
})
export class BookingStepComponent implements OnInit {

  @Input() step: number;

  constructor() { }

  ngOnInit() {
    if (this.step === 1) { document.getElementById('tickets').style.color = '#1BA098'; }
    if (this.step === 2) { document.getElementById('seats').style.color = '#1BA098'; }
    if (this.step === 3) { document.getElementById('account').style.color = '#1BA098'; }
    if (this.step === 4) { document.getElementById('checkout').style.color = '#1BA098'; }
    if (this.step === 5) { document.getElementById('done').style.color = '#1BA098'; }
  }

}
