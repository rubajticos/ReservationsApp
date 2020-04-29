import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { VisitComponent } from './components/visit/visit.component';


const appRoutes: Routes = [
  { path: 'signup', component: RegisterComponent },
  { path: '', component: RegisterComponent },
  { path: 'login', component: LoginComponent},
  { path: 'visits', component: VisitComponent},
  { path: '**', component: PageNotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
