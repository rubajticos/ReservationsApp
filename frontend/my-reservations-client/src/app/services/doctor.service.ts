import { Injectable } from '@angular/core';
import { Doctor } from '../model/doctor';
import { Subject, Observable } from 'rxjs';
import { HttpDoctorService } from './http/http-doctor.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private doctorsListObs = new Subject<Array<Doctor>>();

  constructor(private httpDoctorService: HttpDoctorService) {
    this.getDoctors();
  }

  private getDoctors() {
    this.httpDoctorService.getAllDoctors().subscribe(doctors => {
      this.doctorsListObs.next(doctors);
    });
  }

  public getDoctorsObs(): Observable<Array<Doctor>> {
    return this.doctorsListObs.asObservable();
  }
}
