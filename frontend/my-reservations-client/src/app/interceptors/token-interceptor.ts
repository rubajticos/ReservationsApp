import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpEvent, HttpHandler } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public auth: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (request.url.indexOf('oauth/token') !== -1 || request.url.indexOf('register') !== -1) {
      return next.handle(request);
    }

    request = request.clone({
      setHeaders: {
        Authorization: 'Bearer ' + this.auth.accessToken()
      }
    });

    return next.handle(request);
  }
}
