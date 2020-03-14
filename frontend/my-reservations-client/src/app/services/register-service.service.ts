import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { RegisterModel } from '../model/register-model';

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {

  readonly registerURL = environment.apiBaseUrl + 'register';

  constructor(private http: HttpClient) {

  }

  public registerPatient(patient: RegisterModel) {
    this.http.post(this.registerURL, patient)
      .subscribe(response => {
        console.log(response.toString);
      });
  }



}
