import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Specialization } from 'src/app/model/specialization';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SpecializationService {
  readonly specializationsUrl = environment.apiBaseUrl + 'specializations';

  constructor(private http: HttpClient) {}

  getSpecializations() {
    return this.http.get<Specialization[]>(this.specializationsUrl);
  }
}
