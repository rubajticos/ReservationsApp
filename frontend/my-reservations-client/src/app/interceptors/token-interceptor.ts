import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpEvent,
  HttpHandler,
} from '@angular/common/http';
import { AuthenticationService } from '../auth/authentication.service';
import { Observable } from 'rxjs';
import { exhaust, exhaustMap, take } from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: AuthenticationService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return this.auth.authData.pipe(
      take(1),
      exhaustMap((data) => {
        if (
          !data ||
          request.url.indexOf('oauth/token') !== -1 ||
          request.url.indexOf('register') !== -1
        ) {
          return next.handle(request);
        }

        const modifiedRequest = request.clone({
          setHeaders: {
            Authorization: 'Bearer ' + data.access_token,
          },
        });

        return next.handle(modifiedRequest);
      })
    );
  }
}
