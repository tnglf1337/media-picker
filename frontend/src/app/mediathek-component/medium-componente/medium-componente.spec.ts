import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediumComponente } from './medium-componente';

describe('MediumComponente', () => {
  let component: MediumComponente;
  let fixture: ComponentFixture<MediumComponente>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MediumComponente]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MediumComponente);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
