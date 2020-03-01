import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConcessionItemComponent } from './concession-item.component';

describe('ConcessionItemComponent', () => {
  let component: ConcessionItemComponent;
  let fixture: ComponentFixture<ConcessionItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConcessionItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConcessionItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
