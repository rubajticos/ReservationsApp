import { Patient } from './patient';
import { Doctor } from './doctor';

export class Visit {

  public id: BigInteger;
  public registrationDateTime: Date;
  public dateTime: Date;
  public status: string;
  public patient: Patient;
  public doctor: Doctor;

  constructor() { }

}
