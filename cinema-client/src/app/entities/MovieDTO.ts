export class MovieDTO {
  movieID: number;
  moviePoster: any; // byte[]
  movieTitle: string;
  movieLinkIMDb: string;
  releaseDate: Date;
  movieGenre: string;
  firstDate: Date;

  // tslint:disable-next-line:max-line-length
  constructor(movieID: number, moviePoster: any, movieTitle: string, movieLinkIMDb: string, releaseDate: Date, movieGenre: string, firstDate: Date) {
    this.movieID = movieID;
    this.moviePoster = moviePoster;
    this.movieTitle = movieTitle;
    this.movieLinkIMDb = movieLinkIMDb;
    this.releaseDate = releaseDate;
    this.movieGenre = movieGenre;
    this.firstDate = firstDate;
  }
}
