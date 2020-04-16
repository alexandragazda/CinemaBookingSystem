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
  premiere: boolean;

  constructor() {}
}

export enum AgeRating {
  AG, AP12, N15, M18
}

