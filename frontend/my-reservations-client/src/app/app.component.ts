import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { AuthenticationService } from './auth/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'my-reservations-client';

  constructor(private auth: AuthenticationService) {}

  ngOnInit(): void {
    this.auth.autologin();
  }
}
