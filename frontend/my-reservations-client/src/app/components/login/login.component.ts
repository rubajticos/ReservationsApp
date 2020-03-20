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

  constructor(private loginService: LoginService, private cookieService: CookieService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.loginService.login(this.username, this.password)
      .subscribe(
        (data: AuthorizationModel) => {
          this.saveTokens(data);
        },
        (error: HttpErrorResponse) => {
          console.log('Login error: ' + error.error.message);
        }
      );
  }

  private saveTokens(authModel: AuthorizationModel) {
    this.cookieService.set('access_token', authModel.access_token);
    this.cookieService.set('refresh_token', authModel.refresh_token);
  }

}
