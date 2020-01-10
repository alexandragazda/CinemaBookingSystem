import {AbstractControl, Validators} from '@angular/forms';

// custom validator to check if 2 passwords match
export class PasswordValidator {

  static MatchPassword(abstractControl: AbstractControl) {
    const password = abstractControl.get('password').value;
    const confirmPassword = abstractControl.get('confirmPassword').value;
    if (password !== '' || confirmPassword !== '') {
      if (password !== confirmPassword) {
        abstractControl.get('confirmPassword').setErrors({MatchPassword: true});
      } else {
        abstractControl.get('confirmPassword').setErrors(null);
        return null;
      }
    }
  }

}
