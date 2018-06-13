import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoTreinoListComponent } from './tipo-treino-list.component';

describe('TipoTreinoListComponent', () => {
  let component: TipoTreinoListComponent;
  let fixture: ComponentFixture<TipoTreinoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoTreinoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoTreinoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
