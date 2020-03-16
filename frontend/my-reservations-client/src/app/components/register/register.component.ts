import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient';
import { UserRegister } from 'src/app/model/user-register';
import { RegisterModel } from 'src/app/model/register-model';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  phoneNumberPattern = '\\d+';
  model = new RegisterModel(new UserRegister(), new Patient());

  submitted = false;
  registerSuccess = false;
  registerError = false;
  error: string;
  loading = false;

  constructor(private registerService: RegisterService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    this.registerService.registerPatient(this.model)
      .subscribe(success => {
        this.loading = false;
        this.registerSuccess = true;
      },
        error => {
          this.loading = false;
          this.registerError = true;
          this.error = JSON.stringify(error);
        });
  }

  private resetAlerts() {
    this.registerSuccess = false;
    this.registerError = false;
  }

}
