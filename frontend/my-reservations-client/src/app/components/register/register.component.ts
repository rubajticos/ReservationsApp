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

  model = new RegisterModel(new UserRegister(), new Patient());

  submitted = false;

  constructor(private registerService: RegisterService) { }

  ngOnInit() {
  }

  onSubmit() {
  this.submitted = true;
  this.registerService.registerPatient(this.model);
  }

  get diagnostic() { return JSON.stringify(this.model); }

}
