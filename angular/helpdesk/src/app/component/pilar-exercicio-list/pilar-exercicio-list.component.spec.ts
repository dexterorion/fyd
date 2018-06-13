import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PilarExercicioListComponent } from './pilar-exercicio-list.component';

describe('PilarExercicioListComponent', () => {
  let component: PilarExercicioListComponent;
  let fixture: ComponentFixture<PilarExercicioListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PilarExercicioListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PilarExercicioListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
