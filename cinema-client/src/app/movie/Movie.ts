export class Movie {
  constructor(
    public id: number,
    public title: string,
    public genre: string,
    public cast: string,
    public director: string,
    public ageRating: AgeRating,
    public description: string,
    public releaseDate: Date,
    public endDate: Date,
    public duration: number,
    public trailer: string,
    public poster: any, // byte[]
    public linkIMDb: string,
  ) {}
}

export enum AgeRating {
  AG, AP12, N15, M18
}

