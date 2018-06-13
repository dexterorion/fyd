import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NivelExecucaoNewComponent } from './nivel-execucao-new.component';

describe('NivelExecucaoNewComponent', () => {
  let component: NivelExecucaoNewComponent;
  let fixture: ComponentFixture<NivelExecucaoNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NivelExecucaoNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NivelExecucaoNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
