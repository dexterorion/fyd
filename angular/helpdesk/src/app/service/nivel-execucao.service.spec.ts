import { TestBed, inject } from '@angular/core/testing';

import { NivelExecucaoService } from './nivel-execucao.service';

describe('NivelExecucaoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NivelExecucaoService]
    });
  });

  it('should be created', inject([NivelExecucaoService], (service: NivelExecucaoService) => {
    expect(service).toBeTruthy();
  }));
});
