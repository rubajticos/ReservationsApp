import { UserRegister } from './user-register';
import { Patient } from './patient';

export class RegisterModel {

  constructor(
    public user: UserRegister,
    public patient: Patient) { }

}
