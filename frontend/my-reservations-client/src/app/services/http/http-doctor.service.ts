import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { DoctorService } from '../doctor.service';
import { Observable } from 'rxjs';
import { Doctor } from 'src/app/model/doctor';

@Injectable({
  providedIn: 'root'
})
export class HttpDoctorService {

  readonly doctorsUrl = environment.apiBaseUrl + 'doctor' ;

  constructor(private http: HttpClient) { }

  public getAllDoctors(): Observable<Array<Doctor>> {
    return this.http.get<Array<Doctor>>(this.doctorsUrl);
  }
}
