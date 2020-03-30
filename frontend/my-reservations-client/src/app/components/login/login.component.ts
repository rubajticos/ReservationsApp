import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { HttpErrorResponse, HttpResponse, HttpEvent } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthorizationModel } from 'src/app/model/authorization-model';
import { Router } from '@angular/router';
import { timer, Subscription } from 'rxjs';

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

  constructor(private loginService: LoginService, private cookieService: CookieService, private router: Router) { }

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
    this.cookieService.set('access_token', authModel.access_token);
    this.cookieService.set('refresh_token', authModel.refresh_token);
  }

  private startRedirectCountdown(): void {
    this.countdownTimer = timer(1000, 1000).subscribe(val => {
      this.performCountdownAction();
    });
  }

  private performCountdownAction() {
    this.seconds--;
    if (this.seconds === 0) {
      this.redirectToHome();
      this.countdownTimer.unsubscribe();
    }
  }

  public redirectToHome() {
    this.router.navigate(['/']);
  }

}