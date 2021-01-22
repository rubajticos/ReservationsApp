import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [LoginComponent],
  imports: [
    FormsModule,
    SharedModule,
    CommonModule,
    HttpClientModule,
    LoginRoutingModule,
  ],
  providers: [],
  exports: [LoginComponent],
})
export class LoginModule {}
