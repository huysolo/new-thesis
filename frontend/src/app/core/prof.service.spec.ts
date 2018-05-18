import { TestBed, inject } from '@angular/core/testing';

import { ProfService } from './prof.service';

describe('ProfService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProfService]
    });
  });

  it('should be created', inject([ProfService], (service: ProfService) => {
    expect(service).toBeTruthy();
  }));
});
