import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {

  doctor: string;
  datetime: string;

  constructor() { }

  ngOnInit() {
  }


}
