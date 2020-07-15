import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { Visit } from 'src/app/model/visit';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddVisitComponent } from '../add-visit/add-visit.component';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.scss']
})
export class VisitComponent implements OnInit {

  visitsList: Array<Visit> = [];

  constructor(private visitService: VisitService, private dialog: MatDialog) {
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

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    this.dialog.open(AddVisitComponent, dialogConfig);
  }

}
