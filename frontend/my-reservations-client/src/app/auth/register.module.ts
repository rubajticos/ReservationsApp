import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [RegisterComponent],
  imports: [
    FormsModule,
    SharedModule,
    CommonModule,
    HttpClientModule,
    RegisterRoutingModule,
  ],
  providers: [],
  exports: [RegisterComponent],
})
export class RegisterModule {}
