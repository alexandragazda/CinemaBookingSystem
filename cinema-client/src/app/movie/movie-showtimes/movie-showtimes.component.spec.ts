import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieShowtimesComponent } from './movie-showtimes.component';

describe('MovieShowtimeComponent', () => {
  let component: MovieShowtimesComponent;
  let fixture: ComponentFixture<MovieShowtimesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieShowtimesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieShowtimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
