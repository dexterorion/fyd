import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncaoExercicioListComponent } from './funcao-exercicio-list.component';

describe('FuncaoExercicioListComponent', () => {
  let component: FuncaoExercicioListComponent;
  let fixture: ComponentFixture<FuncaoExercicioListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuncaoExercicioListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuncaoExercicioListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
