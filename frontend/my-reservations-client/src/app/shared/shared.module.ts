import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatchDirective } from './must-match.directive';
import { AlertInfoComponent } from './alertinfo/alertinfo.component';

@NgModule({
  declarations: [MustMatchDirective, AlertInfoComponent],
  imports: [CommonModule],
  exports: [MustMatchDirective, AlertInfoComponent],
})
export class SharedModule {}
