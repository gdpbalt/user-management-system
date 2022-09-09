import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms'

export const repeatePasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password');
  const repeatePassword = control.get('repeatPassword');

  return password && repeatePassword && password.value !== repeatePassword.value ? { repeatePassword: true } : null;
};
