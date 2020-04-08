import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayingNowMovieComponent } from './playing-now-movie.component';

describe('MovieItemComponent', () => {
  let component: PlayingNowMovieComponent;
  let fixture: ComponentFixture<PlayingNowMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayingNowMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayingNowMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
