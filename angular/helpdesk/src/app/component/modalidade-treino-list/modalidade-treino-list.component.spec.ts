import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalidadeTreinoListComponent } from './modalidade-treino-list.component';

describe('ModalidadeTreinoListComponent', () => {
  let component: ModalidadeTreinoListComponent;
  let fixture: ComponentFixture<ModalidadeTreinoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalidadeTreinoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalidadeTreinoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
