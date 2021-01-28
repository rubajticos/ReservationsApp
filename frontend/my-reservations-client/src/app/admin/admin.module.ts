import { NgModule } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { AdminRoutingModule } from './admin-routing.module';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [AdminComponent, HeaderComponent],
  imports: [AdminRoutingModule],
})
export class AdminModule {}
