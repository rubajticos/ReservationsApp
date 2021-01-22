import { Directive, Input } from '@angular/core';
import {
  FormGroup,
  ValidationErrors,
  Validator,
  NG_VALIDATORS,
} from '@angular/forms';
import { MustMatch } from './must-match.validator';

@Directive({
  selector: '[appMustMatch]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: MustMatchDirective, multi: true },
  ],
})
export class MustMatchDirective implements Validator {
  @Input('appMustMatch') mustMatch: string[] = [];

  validate(formGroup: FormGroup): ValidationErrors {
    return MustMatch(this.mustMatch[0], this.mustMatch[1])(formGroup);
  }
}
