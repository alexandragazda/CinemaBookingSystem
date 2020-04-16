import {Component, Input, OnInit} from '@angular/core';
import {Concession} from '../../../entities/Concession';

@Component({
  selector: 'app-concession-item',
  templateUrl: './concession-item.component.html',
  styleUrls: ['./concession-item.component.css']
})
export class ConcessionItemComponent implements OnInit {

  @Input() concession: Concession;

  constructor() { }

  ngOnInit() {
    document.getElementById('id').setAttribute('id', this.concession.id.toString());
  }

  incrementValue() {
    const stringValue = document.getElementById(this.concession.id.toString()).getAttribute('value');
    const value = Number(stringValue) + 1;
    document.getElementById(this.concession.id.toString()).setAttribute('value', value.toString());
  }

  decrementValue() {
    const stringValue = document.getElementById(this.concession.id.toString()).getAttribute('value');
    const value = Number(stringValue) - 1;

    if (value >= 0) {
      document.getElementById(this.concession.id.toString()).setAttribute('value', value.toString());
    }
  }
}
