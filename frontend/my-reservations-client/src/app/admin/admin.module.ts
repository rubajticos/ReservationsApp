import { NgModule } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { AdminRoutingModule } from './admin-routing.module';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { DoctorsComponent } from './doctors/doctors.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AdminComponent, HeaderComponent, DoctorsComponent],
  imports: [AdminRoutingModule, RouterModule, CommonModule, FormsModule],
})
export class AdminModule {}
