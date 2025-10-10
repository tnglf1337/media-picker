import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediathekComponent } from './mediathek-component';

describe('MediathekComponent', () => {
  let component: MediathekComponent;
  let fixture: ComponentFixture<MediathekComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MediathekComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MediathekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
