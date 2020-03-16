export class MovieDTO {
  movieID: number;
  moviePoster: any; // byte[]
  movieTitle: string;
  movieLinkIMDb: string;
  firstDate: Date;


  constructor(movieID: number, moviePoster: any, movieTitle: string, movieLinkIMDb: string, firstDate: Date) {
    this.movieID = movieID;
    this.moviePoster = moviePoster;
    this.movieTitle = movieTitle;
    this.movieLinkIMDb = movieLinkIMDb;
    this.firstDate = firstDate;
  }
}
