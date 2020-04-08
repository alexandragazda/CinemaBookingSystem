import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAvailableComponent } from './all-available.component';

describe('AllAvailableComponent', () => {
  let component: AllAvailableComponent;
  let fixture: ComponentFixture<AllAvailableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllAvailableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAvailableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
