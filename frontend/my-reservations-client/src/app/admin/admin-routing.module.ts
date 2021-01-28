import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';

const appRoutes: Routes = [{ path: '', component: AdminComponent }];

@NgModule({
  imports: [RouterModule.forChild(appRoutes)],
})
export class AdminRoutingModule {}
