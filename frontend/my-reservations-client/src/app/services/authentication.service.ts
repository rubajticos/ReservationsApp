import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthorizationModel } from '../model/authorization-model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  readonly refreshTokenKey: string = 'refresh_token';
  readonly accessTokenKey: string = 'access_token';

  constructor(private cookieService: CookieService) { }

  public saveTokens(authModel: AuthorizationModel) {
    this.cookieService.set(this.accessTokenKey, authModel.access_token);
    this.cookieService.set(this.refreshTokenKey, authModel.refresh_token);
  }

  public refreshToken(): string {
    return this.cookieService.get(this.refreshTokenKey);
  }

  public accessToken(): string {
    return this.cookieService.get(this.accessTokenKey);
  }

  public clearAuthentication(): void {
    this.cookieService.set(this.accessTokenKey, null);
    this.cookieService.set(this.refreshTokenKey, null);
  }

}
