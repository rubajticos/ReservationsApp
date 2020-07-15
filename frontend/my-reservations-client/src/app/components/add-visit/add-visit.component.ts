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
  datetime: string;
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
        datetime: this.datetime
      }
    );
  }

  cancel() {
    this.dialogRef.close();
  }

  public getDoctorDisplayName(doctor: Doctor) {
    return doctor.firstName + ' ' + doctor.lastName + ' - ' + doctor.specialization.name;
  }

}
