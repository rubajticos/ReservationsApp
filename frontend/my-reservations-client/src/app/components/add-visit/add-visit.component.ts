import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DoctorService } from 'src/app/services/doctor.service';
import { Doctor } from 'src/app/model/doctor';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {
  @ViewChild('picker') picker: any;

  public showSpinners = true;
  public stepHour = 1;
  public stepMinute = 30;
  minDate: Date;

  doctors: Array<Doctor>;
  doctor: string;
  date: string;

  constructor(public dialogRef: MatDialogRef<AddVisitComponent>, private doctorsProvider: DoctorService) {
    this.setMinDate();
  }

  ngOnInit() {
    this.doctorsProvider.getDoctors().subscribe(doctors => {
      this.doctors = doctors;
    });
  }

  registerVisit() {
    this.dialogRef.close(
      {
        doctor: this.doctor,
        date: this.date
      }
    );
  }

  cancel() {
    this.dialogRef.close();
  }

  public getDoctorDisplayName(doctor: Doctor) {
    return doctor.firstName + ' ' + doctor.lastName + ' - ' + doctor.specialization.name;
  }

  private setMinDate() {
    this.minDate = new Date();
    this.minDate.setDate(this.minDate.getDate() + 1);
  }

}
