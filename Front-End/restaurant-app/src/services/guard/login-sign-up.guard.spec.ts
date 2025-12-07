import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { loginSignUpGuard } from './login-sign-up.guard';

describe('loginSignUpGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => loginSignUpGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
