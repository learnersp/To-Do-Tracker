import { CanActivateFn } from '@angular/router';

export const authConfigGuard: CanActivateFn = (route, state) => {
  return true;
};
