import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  readonly patientURL = environment.apiBaseUrl + 'patient';

  constructor(private http: HttpClient) { }

  private patient: Patient;

  public getLoggedPatient(): void {
    this.http.get(this.patientURL).subscribe((data: Patient) => {
       this.patient = data;
       localStorage.setItem('patient', JSON.stringify(data));
       localStorage.setItem('patientId', data.id.toString());
       }
    );
  }

  public loggedPatientId(): string {
    return localStorage.getItem('patientId');
  }

  public loggedPatient(): Patient {
    return JSON.parse(localStorage.getItem('patient'));
  }

}
