import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PatientService } from './patient.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { Visit } from '../model/visit';
import { HttpVisitService } from './http/http-visit.service';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  readonly visitsForPatientURL = environment.apiBaseUrl + 'visit/patient=' ;

  private visitsListObs = new BehaviorSubject<Array<Visit>>([]);

  constructor(private httpVisitService: HttpVisitService) { }

  public getVisitsForPatient() {
    this.httpVisitService.getVisitsForPatient().subscribe(visits => {
      this.visitsListObs.next(visits);
    });
  }

  getVisitsObs(): Observable<Array<Visit>> {
    return this.visitsListObs.asObservable();
  }

}
