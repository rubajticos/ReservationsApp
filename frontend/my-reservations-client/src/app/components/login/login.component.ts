import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import {
  HttpErrorResponse,
  HttpResponse,
  HttpEvent,
} from '@angular/common/http';
import { AuthorizationModel } from 'src/app/model/authorization-model';
import { Router } from '@angular/router';
import { timer, Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/auth/authentication.service';
import { PatientService } from 'src/app/services/patient.service';
import { AlertInfoComponent } from '../alertinfo/alertinfo.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  @ViewChild(AlertInfoComponent) alertInfo;

  username: string;
  password: string;

  displayRedirectingInfo = false;
  loading = false;
  seconds = 5;
  countdownTimer: Subscription;

  constructor(
    private authenticationService: AuthenticationService,
    private patientService: PatientService,
    private router: Router
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.resetInfo();
    this.loading = true;
    this.authenticationService.login(this.username, this.password).subscribe(
      (data: AuthorizationModel) => {
        this.loading = false;
        this.displayLoginSuccess();
        this.patientService.getLoggedPatient();
        this.startRedirectCountdown();
      },
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.displayLoginError(error.error.error_description);
      }
    );
  }

  private resetInfo(): void {
    this.displayRedirectingInfo = false;
    this.alertInfo.clear();
  }

  private startRedirectCountdown(): void {
    this.countdownTimer = timer(1000, 1000).subscribe((val) => {
      this.performCountdownAction();
    });
  }

  private performCountdownAction() {
    this.seconds--;
    if (this.seconds === 0) {
      this.redirectToVisits();
      this.countdownTimer.unsubscribe();
    }
  }

  public redirectToVisits() {
    this.router.navigate(['visits']);
  }

  private displayLoginSuccess() {
    this.displayRedirectingInfo = true;
    this.alertInfo.showSuccess('Success! You are logged succesfully!');
  }
  private displayLoginError(error: string) {
    this.displayRedirectingInfo = false;
    this.alertInfo.showError(error);
  }
}
