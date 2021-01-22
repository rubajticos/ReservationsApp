import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavheaderComponent } from './components/navheader/navheader.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from './auth/authentication.service';
import { TokenInterceptor } from './auth/token-interceptor';
import { VisitComponent } from './components/visit/visit.component';
import { PatientService } from './services/patient.service';
import { HttpVisitService } from './services/http/http-visit.service';
import { VisitService } from './services/visit.service';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddVisitComponent } from './components/add-visit/add-visit.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import {
  MatMomentDateModule,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import { MatInputModule } from '@angular/material/input';
import { DoctorService } from './services/doctor.service';
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
} from '@angular-material-components/datetime-picker';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SharedModule } from './shared/shared.module';
import { LoginModule } from './auth/login.module';
import { RegisterModule } from './auth/register.module';

@NgModule({
  declarations: [
    AppComponent,
    NavheaderComponent,
    FooterComponent,
    PageNotFoundComponent,
    VisitComponent,
    AddVisitComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatMomentDateModule,
    MatInputModule,
    BrowserAnimationsModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    MatProgressSpinnerModule,
    SharedModule,
    LoginModule,
    RegisterModule,
  ],
  providers: [
    CookieService,
    AuthenticationService,
    PatientService,
    VisitService,
    HttpVisitService,
    DoctorService,
    DatePipe,
    MatSnackBar,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: MAT_DATE_LOCALE, useValue: 'pl-PL' },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } },
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddVisitComponent],
})
export class AppModule {}
