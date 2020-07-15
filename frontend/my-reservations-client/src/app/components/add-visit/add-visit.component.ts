import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {

  doctor: string;
  datetime: string;

  constructor(public dialogRef: MatDialogRef<AddVisitComponent>) { }

  ngOnInit() {
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

}
