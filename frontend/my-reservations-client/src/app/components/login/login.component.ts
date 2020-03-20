import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { HttpErrorResponse, HttpResponse, HttpEvent } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthorizationModel } from 'src/app/model/authorization-model';

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

  constructor(private loginService: LoginService, private cookieService: CookieService) { }

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

}
