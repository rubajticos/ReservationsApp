import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { NavheaderComponent } from './components/navheader/navheader.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { MustMatchDirective } from './directives/must-match.directive';
import { RegisterService } from './services/register.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login.service';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from './services/authentication.service';
import { TokenInterceptor } from './interceptors/token-interceptor';
import { VisitComponent } from './components/visit/visit.component';
import { PatientService } from './services/patient.service';
import { HttpVisitService } from './services/http/http-visit.service';
import { VisitService } from './services/visit.service';
import { AlertInfoComponent } from './components/alertinfo/alertinfo.component';
import {MatDialogModule} from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddVisitComponent } from './components/add-visit/add-visit.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { DoctorService } from './services/doctor.service';

@NgModule({
  declarations: [
    AppComponent,
    NavheaderComponent,
    FooterComponent,
    PageNotFoundComponent,
    RegisterComponent,
    MustMatchDirective,
    LoginComponent,
    VisitComponent,
    AlertInfoComponent,
    AddVisitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [RegisterService, LoginService, CookieService, AuthenticationService, PatientService, VisitService, HttpVisitService,
    DoctorService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}],
  bootstrap: [AppComponent],
  entryComponents: [AddVisitComponent]
})
export class AppModule { }
