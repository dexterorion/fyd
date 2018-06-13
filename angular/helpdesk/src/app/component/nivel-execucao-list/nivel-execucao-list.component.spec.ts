import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NivelExecucaoListComponent } from './nivel-execucao-list.component';

describe('NivelExecucaoListComponent', () => {
  let component: NivelExecucaoListComponent;
  let fixture: ComponentFixture<NivelExecucaoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NivelExecucaoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NivelExecucaoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
