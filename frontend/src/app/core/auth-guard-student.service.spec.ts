import { TestBed, inject } from '@angular/core/testing';

import { AuthGuardStudentService } from './auth-guard-student.service';

describe('AuthGuardStudentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthGuardStudentService]
    });
  });

  it('should be created', inject([AuthGuardStudentService], (service: AuthGuardStudentService) => {
    expect(service).toBeTruthy();
  }));
});
