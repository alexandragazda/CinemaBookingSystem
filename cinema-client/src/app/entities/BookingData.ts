export class BookingData {
  showtimeID: number;
  movieTitle: string;
  moviePoster: any;
  nrChildTicket: number;
  nrStudentTicket: number;
  nrAdultTicket: number;
  nrRetiredTicket: number;
  totalPrice: number;


  // tslint:disable-next-line:max-line-length
  constructor(showtimeID: number, movieTitle: string, moviePoster: any, nrChildTicket: number, nrStudentTicket: number, nrAdultTicket: number, nrRetiredTicket: number, totalPrice: number) {
    this.showtimeID = showtimeID;
    this.movieTitle = movieTitle;
    this.moviePoster = moviePoster;
    this.nrChildTicket = nrChildTicket;
    this.nrStudentTicket = nrStudentTicket;
    this.nrAdultTicket = nrAdultTicket;
    this.nrRetiredTicket = nrRetiredTicket;
    this.totalPrice = totalPrice;
  }
}
