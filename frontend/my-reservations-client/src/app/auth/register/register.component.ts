import { Component, OnInit, ViewChild } from '@angular/core';
import { Patient } from 'src/app/model/patient';
import { UserRegister } from 'src/app/auth/user-register';
import { RegisterModel } from 'src/app/auth/register-model';
import { HttpErrorResponse } from '@angular/common/http';
import { AlertInfoComponent } from '../../components/alertinfo/alertinfo.component';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  @ViewChild(AlertInfoComponent) alertInfo;

  phoneNumberPattern = '\\d+';
  model = new RegisterModel(new UserRegister(), new Patient());
  repassword: string;

  submitted = false;
  loading = false;
  hideForm = false;
  displayRegisteredData = false;

  constructor(private auth: AuthenticationService) {}

  ngOnInit() {}

  onSubmit() {
    this.alertInfo.clear();
    this.submitted = true;
    this.loading = true;
    this.auth.registerPatient(this.model).subscribe(
      (success) => {
        this.loading = false;
        this.hideForm = true;
        this.showRegisterSuccess();
      },
      (error: HttpErrorResponse) => {
        this.loading = false;
        this.showRegisterError(error.error.message);
      }
    );
  }

  private showRegisterSuccess() {
    this.alertInfo.showSuccess('Success! You are registered succesfully!');
    this.displayRegisteredData = true;
  }

  private showRegisterError(error: string) {
    this.alertInfo.showError(error);
  }
}
