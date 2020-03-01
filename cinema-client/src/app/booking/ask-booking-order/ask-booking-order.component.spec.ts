import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AskBookingOrderComponent } from './ask-booking-order.component';

describe('AskBookingOrderComponent', () => {
  let component: AskBookingOrderComponent;
  let fixture: ComponentFixture<AskBookingOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AskBookingOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AskBookingOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
