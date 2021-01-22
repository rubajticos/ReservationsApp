import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';

const appRoutes: Routes = [{ path: '', component: RegisterComponent }];

@NgModule({
  imports: [RouterModule.forChild(appRoutes)],
})
export class RegisterRoutingModule {}
