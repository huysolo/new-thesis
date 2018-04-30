import { TestBed, inject } from '@angular/core/testing';

import { CheckloginGuard} from './checklogin.guard.service';

describe('Checklogin.GuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CheckloginGuard]
    });
  });

  it('should be created', inject([CheckloginGuard], (service: CheckloginGuard) => {
    expect(service).toBeTruthy();
  }));
});
