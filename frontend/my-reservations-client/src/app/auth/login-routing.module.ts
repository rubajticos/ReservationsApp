import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';

const appRoutes: Routes = [{ path: '', component: LoginComponent }];

@NgModule({
  imports: [RouterModule.forChild(appRoutes)],
})
export class LoginRoutingModule {}