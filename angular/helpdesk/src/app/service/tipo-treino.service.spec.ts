import { TestBed, inject } from '@angular/core/testing';

import { TipoTreinoService } from './tipo-treino.service';

describe('TipoTreinoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TipoTreinoService]
    });
  });

  it('should be created', inject([TipoTreinoService], (service: TipoTreinoService) => {
    expect(service).toBeTruthy();
  }));
});
