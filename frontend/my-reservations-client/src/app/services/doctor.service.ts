import { Injectable } from '@angular/core';
import { Doctor } from '../model/doctor';
import { Subject, Observable } from 'rxjs';
import { HttpDoctorService } from './http/http-doctor.service';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private httpDoctorService: HttpDoctorService) {
    this.getDoctors();
    console.log("Doctors Constructor");
  }

  public getDoctors(): Observable<Array<Doctor>> {
    return this.httpDoctorService.getAllDoctors();
  }
}
