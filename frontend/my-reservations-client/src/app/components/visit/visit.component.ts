import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { Visit } from 'src/app/model/visit';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.scss']
})
export class VisitComponent implements OnInit {

  visitsList: Array<Visit> = [];

  constructor(private visitService: VisitService) {
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
    console.log("Add visit!");
  }

}
