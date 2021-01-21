import { UserRegister } from '../model/user-register';
import { Patient } from '../model/patient';

export class RegisterModel {
  constructor(public userCredentials: UserRegister, public patient: Patient) {}
}
