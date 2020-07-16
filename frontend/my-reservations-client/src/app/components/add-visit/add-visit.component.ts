import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DoctorService } from 'src/app/services/doctor.service';
import { Doctor } from 'src/app/model/doctor';


@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {

  doctor: string;
  date: string;
  time: string;
  doctors: Array<Doctor>;

  constructor(public dialogRef: MatDialogRef<AddVisitComponent>, private doctorsProvider: DoctorService) { }

  ngOnInit() {
    this.doctorsProvider.getDoctors().subscribe(doctors => {
      this.doctors = doctors;
    });
  }

  registerVisit() {
    this.dialogRef.close(
      {
        doctor: this.doctor,
        date: this.date,
        time: this.time
      }
    );
  }

  cancel() {
    this.dialogRef.close();
  }

  public getDoctorDisplayName(doctor: Doctor) {
    return doctor.firstName + ' ' + doctor.lastName + ' - ' + doctor.specialization.name;
  }

  public getAvailableHours(): string[] {
    return ['08:00', '08:30', '09:00', '09:30', '10:00', '10:30',
      '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00',
      '14:30', '15:00', '15:30'];
  }

}
