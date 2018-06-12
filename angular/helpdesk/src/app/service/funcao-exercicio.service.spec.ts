import { TestBed, inject } from '@angular/core/testing';

import { FuncaoExercicioService } from './funcao-exercicio.service';

describe('FuncaoExercicioService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FuncaoExercicioService]
    });
  });

  it('should be created', inject([FuncaoExercicioService], (service: FuncaoExercicioService) => {
    expect(service).toBeTruthy();
  }));
});
