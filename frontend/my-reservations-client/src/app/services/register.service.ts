import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { RegisterModel } from '../model/register-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  readonly registerURL = environment.apiBaseUrl + 'register';

  constructor(private http: HttpClient) {

  }

  public registerPatient(patient: RegisterModel): Observable<any> {
    return this.http.post(this.registerURL, patient);
  }



}
