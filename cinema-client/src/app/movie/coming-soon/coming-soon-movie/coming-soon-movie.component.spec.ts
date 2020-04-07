import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComingSoonMovieComponent } from './coming-soon-movie.component';

describe('ComingSoonMovieComponent', () => {
  let component: ComingSoonMovieComponent;
  let fixture: ComponentFixture<ComingSoonMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComingSoonMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComingSoonMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
