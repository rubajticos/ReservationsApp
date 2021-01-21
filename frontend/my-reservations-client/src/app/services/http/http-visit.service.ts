import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PatientService } from '../patient.service';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Visit } from 'src/app/model/visit';

@Injectable({
  providedIn: 'root'
})
export class HttpVisitService {

  readonly visitsURL = environment.apiBaseUrl + 'visit' ;
  readonly visitsForPatientURL = environment.apiBaseUrl + 'visit/patient=' ;

  constructor(private http: HttpClient, private patientService: PatientService) { }

  public getVisitsForPatient(): Observable<Array<Visit>> {
    const patientId = this.patientService.loggedPatientId();
    return this.http.get<Array<Visit>>(this.visitsForPatientURL + patientId);
  }

  public createVisit(visit: VisitCreationData): Observable<any> {
    return this.http.post(this.visitsURL, visit);
  }

}
