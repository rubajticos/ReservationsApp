import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DoctorService } from 'src/app/services/doctor.service';
import { Doctor } from 'src/app/model/doctor';
import { FormControl } from '@angular/forms';
import { DatePipe, Time } from '@angular/common';
import { Moment } from 'moment';


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
  date: Moment;
  hour: string;

  constructor(public dialogRef: MatDialogRef<AddVisitComponent>, private doctorsProvider: DoctorService, private datepipe: DatePipe) {
    this.setMinDate();
  }

  ngOnInit() {
    this.doctorsProvider.getDoctors().subscribe(doctors => {
      this.doctors = doctors;
    });
  }

  registerVisit() {
    const visitDate = this.buildVisitDate();
    this.dialogRef.close(
      {
        doctorId: this.doctor,
        dateTime: this.datepipe.transform(visitDate, 'yyyy-MM-ddTHH:mm:ss')
      }
    );
  }

  private buildVisitDate() {
    const visitDate = this.date.toDate();
    const [hours, minutes] = this.hour.split(':');
    visitDate.setHours(+hours);
    visitDate.setMinutes(+minutes);
    return visitDate;
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

  public getAvailableHours(): string[] {
    return ['08:00', '08:30', '09:00', '09:30', '10:00', '10:30',
      '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00',
      '14:30', '15:00', '15:30'];
  }

}
