import { Directive, Input } from '@angular/core';
import { FormGroup, ValidationErrors } from '@angular/forms';
import { MustMatch } from '../validators/must-match.validator';

@Directive({
  selector: '[appMustMatch]'
})
export class MustMatchDirective {

  @Input('appMustMatch') mustMatch: string[] = [];

    validate(formGroup: FormGroup): ValidationErrors {
        return MustMatch(this.mustMatch[0], this.mustMatch[1])(formGroup);
    }

}
