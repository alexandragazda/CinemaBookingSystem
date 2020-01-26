export class Movie {
  id: number;
  title: string;
  genre: string;
  cast: string;
  director: string;
  ageRating: AgeRating;
  description: string;
  releaseDate: Date;
  availableTechnology: string;
  duration: number;
  trailer: string;
  poster: any; // byte[]
  linkIMDb: string;

  constructor() {
  }
  // constructor(
  //   public id: number,
  //   public title: string,
  //   public genre: string,
  //   public cast: string,
  //   public director: string,
  //   public ageRating: AgeRating,
  //   public description: string,
  //   public releaseDate: Date,
  //   public endDate: Date,
  //   public availableTechnology: string,
  //   public duration: number,
  //   public trailer: string,
  //   public poster: any, // byte[]
  //   public linkIMDb: string,
  // ) {}
}

export enum AgeRating {
  AG, AP12, N15, M18
}

