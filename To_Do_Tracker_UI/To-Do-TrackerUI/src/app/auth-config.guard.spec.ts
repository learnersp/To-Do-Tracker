import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authConfigGuard } from './auth-config.guard';

describe('authConfigGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authConfigGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
