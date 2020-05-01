import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-alertinfo',
  templateUrl: './alertinfo.component.html',
  styleUrls: ['./alertinfo.component.scss']
})
export class AlertInfoComponent implements OnInit {

  protected displaySuccess = false;
  protected displayError = false;
  protected successMessage: string;
  protected errorMessage: string;

  constructor() { }

  ngOnInit() {
  }

  public showSuccess(message: string) {
    this.clear();
    this.displaySuccess = true;
    this.successMessage = message;
  }

  public showError(message: string) {
    this.clear();
    this.displayError = true;
    this.errorMessage = message;
  }

  public clear() {
    this.displaySuccess = false;
    this.displayError = false;
    this.successMessage = '';
    this.errorMessage = '';
  }

}
