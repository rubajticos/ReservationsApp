import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly loginURL = environment.apiBaseUrl + 'oauth/token';

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<object> {
    console.log(username + '::' + password);

    const headers = {
      'Authorization': 'Basic ' + btoa('my-client:my-secret'),
      'Content-Type': 'application/x-www-form-urlencoded'
    };

    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', username)
      .set('password', password);

    return this.http.post(this.loginURL, body, { headers });
  }
}
