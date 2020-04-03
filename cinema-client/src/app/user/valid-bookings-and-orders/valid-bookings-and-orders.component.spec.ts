import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidBookingsAndOrdersComponent } from './valid-bookings-and-orders.component';

describe('ValidBookingsAndOrdersComponent', () => {
  let component: ValidBookingsAndOrdersComponent;
  let fixture: ComponentFixture<ValidBookingsAndOrdersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidBookingsAndOrdersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidBookingsAndOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
