import { TestBed, inject } from '@angular/core/testing';

import { ModalidadeTreinoService } from './modalidade-treino.service';

describe('ModalidadeTreinoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModalidadeTreinoService]
    });
  });

  it('should be created', inject([ModalidadeTreinoService], (service: ModalidadeTreinoService) => {
    expect(service).toBeTruthy();
  }));
});
