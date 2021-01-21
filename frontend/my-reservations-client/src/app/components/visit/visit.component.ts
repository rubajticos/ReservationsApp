import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { Visit } from 'src/app/model/visit';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddVisitComponent } from '../add-visit/add-visit.component';
import { PatientService } from 'src/app/services/patient.service';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.scss']
})
export class VisitComponent implements OnInit {

  visitsList: Array<Visit> = [];
  loading = false;

  constructor(
    private visitService: VisitService, private patientService: PatientService,
    private dialog: MatDialog, private datepipe: DatePipe, private snackBar: MatSnackBar) {
    this.visitService.getVisitsForPatient();
    this.visitService.getVisitsObs().subscribe(visits => {
      this.visitsList = visits;
      console.log(JSON.stringify(this.visitsList));
    });
  }

  ngOnInit() {
  }

  public printVisit(visit: Visit): string {
    return JSON.stringify(visit);
  }

  public showAddVisit() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(AddVisitComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        this.createVisit(data);
        console.log('Dialog output: ' + JSON.stringify(data));
      }
    );
  }

  createVisit(data: VisitCreationData) {
    this.loading = true;
    const dataForRegister = data;
    dataForRegister.registrationDateTime = this.datepipe.transform(new Date(), 'yyyy-MM-ddTHH:mm:ss');
    dataForRegister.status = 'NEW';
    dataForRegister.patientId = this.patientService.loggedPatientId();

    this.visitService.saveVisit(dataForRegister)
      .subscribe(success => {
        this.showAddVisitSuccess();
        this.visitService.getVisitsForPatient();
        this.loading = false;
      },
        (error: HttpErrorResponse) => {
          this.showAddVisitError(error.error.message);
          console.log(error.error.message);
          this.loading = false;
        });

  }

  showAddVisitSuccess() {
    this.snackBar.open('The visit has been registered!');
  }

  showAddVisitError(info: string) {
    this.snackBar.open(info);
  }

}
