export class TicketType {
  id: TicketTypeEnum;
  price2D: number;
  price3D: number;
}

export enum TicketTypeEnum {
  Child, Student, Adult, Retired
}
