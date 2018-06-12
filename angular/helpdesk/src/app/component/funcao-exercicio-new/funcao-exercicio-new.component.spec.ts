import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncaoExercicioCreateComponent } from './funcao-exercicio-create.component';

describe('FuncaoExercicioCreateComponent', () => {
  let component: FuncaoExercicioCreateComponent;
  let fixture: ComponentFixture<FuncaoExercicioCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuncaoExercicioCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuncaoExercicioCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
