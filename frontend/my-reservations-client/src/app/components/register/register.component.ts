import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient';
import { UserRegister } from 'src/app/model/user-register';
import { RegisterModel } from 'src/app/model/register-model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  model = new RegisterModel(new UserRegister(), new Patient());

  submitted = false;

  constructor() { }

  ngOnInit() {
  }

  onSubmit() { this.submitted = true; }

  get diagnostic() { return JSON.stringify(this.model); }

}
