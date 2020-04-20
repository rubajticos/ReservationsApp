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
    this.http.get(this.patientURL).subscribe((data: Patient) => this.patient = data);
  }

  public loggedPatient(): Patient {
    return this.patient;
  }

}
