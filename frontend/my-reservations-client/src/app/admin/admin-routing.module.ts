import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DoctorsComponent } from './doctors/doctors.component';

const appRoutes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [{ path: 'doctors', component: DoctorsComponent }],
  },
];

@NgModule({
  imports: [RouterModule.forChild(appRoutes)],
})
export class AdminRoutingModule {}
