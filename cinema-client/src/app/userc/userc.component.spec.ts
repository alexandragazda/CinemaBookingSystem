import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsercComponent } from './userc.component';

describe('UsercComponent', () => {
  let component: UsercComponent;
  let fixture: ComponentFixture<UsercComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsercComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsercComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
