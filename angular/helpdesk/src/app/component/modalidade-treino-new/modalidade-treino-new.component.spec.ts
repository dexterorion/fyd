import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalidadeTreinoNewComponent } from './modalidade-treino-new.component';

describe('ModalidadeTreinoNewComponent', () => {
  let component: ModalidadeTreinoNewComponent;
  let fixture: ComponentFixture<ModalidadeTreinoNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalidadeTreinoNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalidadeTreinoNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
