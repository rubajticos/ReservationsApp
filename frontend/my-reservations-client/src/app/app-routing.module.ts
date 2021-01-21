import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { VisitComponent } from './components/visit/visit.component';
import { AuthGuard } from './auth/auth-guard';

const appRoutes: Routes = [
  { path: 'signup', component: RegisterComponent },
  { path: '', component: VisitComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'visits', component: VisitComponent, canActivate: [AuthGuard] },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
