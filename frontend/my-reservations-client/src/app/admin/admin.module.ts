import { NgModule } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { AdminRoutingModule } from './admin-routing.module';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { DoctorsComponent } from './doctors/doctors.component';

@NgModule({
  declarations: [AdminComponent, HeaderComponent, DoctorsComponent],
  imports: [AdminRoutingModule, RouterModule],
})
export class AdminModule {}
