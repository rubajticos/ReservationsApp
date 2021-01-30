import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/model/doctor';
import { Specialization } from 'src/app/model/specialization';
import { DoctorService } from 'src/app/services/doctor.service';
import { DoctorCreateDTO } from './DoctorCreateDTO';
import { SpecializationService } from './specialization.service';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.scss'],
})
export class DoctorsComponent implements OnInit {
  doctors: Doctor[];
  newDoctor: DoctorCreateDTO;
  specializations: Specialization[];

  constructor(
    private doctorService: DoctorService,
    private specializationService: SpecializationService
  ) {
    this.specializationService
      .getSpecializations()
      .subscribe((specs) => (this.specializations = specs));
    this.newDoctor = new DoctorCreateDTO();
  }

  ngOnInit(): void {
    this.doctorService.getDoctors().subscribe((d) => {
      this.doctors = d;
      console.log(JSON.stringify(this.doctors));
    });
  }

  onAddNewDoctor() {
    if (this.newDoctor.isValid()) {
      this.doctorService.createNewDoctor(this.newDoctor);
    } else {
      console.log('New doctor data is invalid');
    }
  }
}
