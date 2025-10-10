import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediumErstellenForm } from './medium-erstellen-form';

describe('MediumErstellenForm', () => {
  let component: MediumErstellenForm;
  let fixture: ComponentFixture<MediumErstellenForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MediumErstellenForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MediumErstellenForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
