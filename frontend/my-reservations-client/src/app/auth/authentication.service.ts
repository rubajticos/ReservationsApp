import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthorizationModel } from './authorization-model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  readonly loginURL = environment.apiBaseUrl + 'oauth/token';
  readonly cookieKey = 'auth';

  authData = new BehaviorSubject<AuthorizationModel>(null);

  constructor(private cookieService: CookieService, private http: HttpClient) {}

  login(username: string, password: string) {
    const headers = {
      Authorization: 'Basic ' + btoa('my-client:my-secret'),
      'Content-Type': 'application/x-www-form-urlencoded',
    };

    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', username)
      .set('password', password);

    return this.http
      .post<AuthorizationModel>(this.loginURL, body, { headers })
      .pipe(
        catchError(this.handleError),
        tap((resData) => {
          this.handleAuthentication(resData);
        })
      );
  }

  // public clearAuthentication(): void {
  //   this.cookieService.set(this.accessTokenKey, null);
  //   this.cookieService.set(this.refreshTokenKey, null);
  // }
  autologin() {
    const authData: AuthorizationModel = JSON.parse(
      this.cookieService.get(this.cookieKey)
    );

    if (!authData) {
      return;
    }

    this.authData.next(authData);
  }

  private handleAuthentication(auth: AuthorizationModel) {
    this.authData.next(auth);
    this.cookieService.set(this.cookieKey, JSON.stringify(auth));
  }

  private handleError(errorRes: HttpErrorResponse) {
    let errorMessage = 'An unknown error occured!';
    if (!errorRes.error || !errorRes.error.error) {
      return throwError(errorMessage);
    }

    switch (errorRes.error.error.message) {
      case 'EMAIL_EXISTS':
        errorMessage = 'This email exists already';
        break;
      case 'EMAIL_NOT_FOUND':
        errorMessage = 'This email does not exist';
        break;
      case 'INVALID_PASSWORD':
        errorMessage = 'This password is not correct.';
        break;
      case 'USER_DISABLED':
        errorMessage = 'Account is disabled';
        break;
    }

    return throwError(errorMessage);
  }
}
