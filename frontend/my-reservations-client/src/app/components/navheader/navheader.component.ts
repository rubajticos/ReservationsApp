import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/auth/authentication.service';

@Component({
  selector: 'app-navheader',
  templateUrl: './navheader.component.html',
  styleUrls: ['./navheader.component.scss'],
})
export class NavheaderComponent implements OnInit, OnDestroy {
  authSub: Subscription;
  isAuthenticated = false;

  constructor(private auth: AuthenticationService) {}

  ngOnInit() {
    this.authSub = this.auth.authData.subscribe((data) => {
      if (!data) {
        this.isAuthenticated = false;
      } else {
        this.isAuthenticated = true;
      }
    });
  }

  onLogout() {
    console.log('Logout!');
  }

  ngOnDestroy(): void {
    this.authSub.unsubscribe();
  }
}
