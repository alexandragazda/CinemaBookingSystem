import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AskLoginComponent } from './ask-login.component';

describe('AskLoginComponent', () => {
  let component: AskLoginComponent;
  let fixture: ComponentFixture<AskLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AskLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AskLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
