import { TypeCheckCompiler } from '@angular/compiler/src/view_compiler/type_check_compiler';

export class Specialization {
  public id: BigInteger;
  public type: string;
  public name: string;

  constructor(id: BigInteger, type: string, name: string) {
    this.id = id;
    this.type = type;
    this.name = name;
  }
}
