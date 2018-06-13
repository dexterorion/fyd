import { TestBed, inject } from '@angular/core/testing';

import { PilarExercicioService } from './pilar-exercicio.service';

describe('PilarExercicioService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PilarExercicioService]
    });
  });

  it('should be created', inject([PilarExercicioService], (service: PilarExercicioService) => {
    expect(service).toBeTruthy();
  }));
});
