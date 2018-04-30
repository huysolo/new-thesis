import { TestBed, inject } from '@angular/core/testing';

import { AuthTopicGuardService } from './auth-topic-guard.service';

describe('AuthTopicGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthTopicGuardService]
    });
  });

  it('should be created', inject([AuthTopicGuardService], (service: AuthTopicGuardService) => {
    expect(service).toBeTruthy();
  }));
});
