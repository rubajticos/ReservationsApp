import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { VisitComponent } from './components/visit/visit.component';
import { AuthGuard } from './auth/auth-guard';

const appRoutes: Routes = [
  { path: 'signup', loadChildren: './auth/register.module#RegisterModule' },
  { path: '', component: VisitComponent, canActivate: [AuthGuard] },
  { path: 'login', loadChildren: './auth/login.module#LoginModule' },
  { path: 'visits', component: VisitComponent, canActivate: [AuthGuard] },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
