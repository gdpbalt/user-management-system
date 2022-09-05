import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms'

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const password = control.value;
    if (!password) {
      return null;
    }
    const hasOneCharacter = /[A-Za-z]+/.test(password);
    const hasOneNumber = /[0-9]+/.test(password);
    const passwordValid = hasOneCharacter && hasOneNumber;
    return !passwordValid ? { passwordValid: true } : null;
  };
}
