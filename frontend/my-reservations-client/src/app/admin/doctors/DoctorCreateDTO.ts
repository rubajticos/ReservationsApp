import { Specialization } from 'src/app/model/specialization';

export class DoctorCreateDTO {
  public firstName: string;
  public lastName: string;
  public specializationId: string;

  constructor() {}

  public isValid() {
    console.log(JSON.stringify(this));

    return (
      this.firstName &&
      this.firstName.trim() &&
      this.lastName &&
      this.lastName.trim() &&
      this.specializationId &&
      this.specializationId.trim()
    );
  }
}
