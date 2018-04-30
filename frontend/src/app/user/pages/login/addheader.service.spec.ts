import { TestBed, inject } from '@angular/core/testing';

import { AddheaderService } from './addheader.service';

describe('AddheaderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddheaderService]
    });
  });

  it('should be created', inject([AddheaderService], (service: AddheaderService) => {
    expect(service).toBeTruthy();
  }));
});
