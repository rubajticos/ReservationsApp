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
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login.service';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    NavheaderComponent,
    FooterComponent,
    PageNotFoundComponent,
    RegisterComponent,
    MustMatchDirective,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [RegisterService, LoginService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
