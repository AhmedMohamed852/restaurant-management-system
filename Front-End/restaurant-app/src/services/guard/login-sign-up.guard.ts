import { CanActivateFn, Router } from '@angular/router';

export const loginSignUpGuard: CanActivateFn = (route, state) => {
  const router = new Router();

  const token = sessionStorage.getItem("token");

  // لو فيه توكن → ممنوع يدخل صفحة اللوجين/ساين أب
  if (token) {
    router.navigate(['/products']);
    return false;
  }

  return true;
};
