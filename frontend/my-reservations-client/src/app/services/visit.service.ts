import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Visit } from '../model/visit';
import { HttpVisitService } from './http/http-visit.service';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  private visitsListObs = new BehaviorSubject<Array<Visit>>([]);

  constructor(private httpVisitService: HttpVisitService) { }

  public getVisitsForPatient() {
    this.httpVisitService.getVisitsForPatient().subscribe(visits => {
      this.visitsListObs.next(visits);
    });
  }

  public getVisitsObs(): Observable<Array<Visit>> {
    return this.visitsListObs.asObservable();
  }

  public saveVisit(visit: VisitCreationData): Observable<any> {
    return this.httpVisitService.createVisit(visit);
  }

}
