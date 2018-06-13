import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PilarExercicioNewComponent } from './pilar-exercicio-new.component';

describe('PilarExercicioNewComponent', () => {
  let component: PilarExercicioNewComponent;
  let fixture: ComponentFixture<PilarExercicioNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PilarExercicioNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PilarExercicioNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
