import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly loginURL = environment.baseUrl + 'oauth/token';

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<object> {
    const headers = {
      'Content-type': 'application/x-www-form-urlencoded'
    };

    const body = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');

    return this.http.post(this.loginURL, body, {headers});
  }
}
