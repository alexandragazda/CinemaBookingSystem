import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoRegistrationComponent } from './no-registration.component';

describe('NoRegistrationComponent', () => {
  let component: NoRegistrationComponent;
  let fixture: ComponentFixture<NoRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoRegistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
