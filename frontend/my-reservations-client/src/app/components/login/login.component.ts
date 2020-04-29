import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { HttpErrorResponse, HttpResponse, HttpEvent } from '@angular/common/http';
import { AuthorizationModel } from 'src/app/model/authorization-model';
import { Router } from '@angular/router';
import { timer, Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  displaySuccess = false;
  displayError = false;
  errorMessage: string;
  loading = false;
  seconds = 5;
  countdownTimer: Subscription;

  constructor(private loginService: LoginService,
              private authenticationService: AuthenticationService,
              private patientService: PatientService,
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.resetInfo();
    this.loading = true;
    this.loginService.login(this.username, this.password)
      .subscribe(
        (data: AuthorizationModel) => {
          this.loading = false;
          this.displaySuccess = true;
          this.saveTokens(data);
          this.patientService.getLoggedPatient();
          this.startRedirectCountdown();
        },
        (error: HttpErrorResponse) => {
          this.loading = false;
          this.displayError = true;
          this.errorMessage = error.error.message;
        }
      );
  }

  private resetInfo(): void {
    this.displaySuccess = false;
    this.displayError = false;
  }

  private saveTokens(authModel: AuthorizationModel) {
    this.authenticationService.saveTokens(authModel);
  }

  private startRedirectCountdown(): void {
    this.countdownTimer = timer(1000, 1000).subscribe(val => {
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

}
