import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoTreinoNewComponent } from './tipo-treino-new.component';

describe('TipoTreinoNewComponent', () => {
  let component: TipoTreinoNewComponent;
  let fixture: ComponentFixture<TipoTreinoNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoTreinoNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoTreinoNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
