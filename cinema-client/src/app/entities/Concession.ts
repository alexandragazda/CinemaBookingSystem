import {ConcessionType} from './ConcessionType';

export class Concession {
  id: number;
  name: string;
  description: string;
  // size: Size;
  price: number;
  concessionType: ConcessionType;
}

// export enum Size {
//   Small, Medium, Large
// }
