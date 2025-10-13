// ##### ENUMS #####
export enum MediumTyp {
  SERIE, FILM, ANIME, MANGA, VIDEOSPIEL, BUCH, PODCAST
}
export enum Status {
  BEENDET = "BEENDET",
  AM_SCHAUEN = "AM_SCHAUEN",
  AM_SPIELEN = "AM_SPIELEN",
  AM_LESEN = "AM_LESEN",
  AM_HOEREN = "AM_HOEREN",
  GEPLANT = "GEPLANT",
  PAUSIERT = "PAUSIERT",
  ABGEBROCHEN = "ABGEBROCHEN",
}
export enum Platform {
  PLAYSTATION_4= "Playstation 4",
  PLAYSTATION_5 = "Playstation 5",
  PLAYSTATION_6 = "Playstation 6",
  PC = "PC",
  NINTENDO_SWITCH = "Nintendo Switch",
  NINTENDO_SWITCH_2 = "Nintendo Switch 2",
}

// ##### KLASSEN #####
export class Medium {
  constructor(
    private mediumId : string,
    private erstelltAm : string,
    private titel : string,
    private mediumTyp : MediumTyp,
    private status : Status,
    private rating : number,
    private notiz : string[]
    ) {
  }
}

export class Serie extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    season : number,
    folgen: number,
    currentFolge : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.SERIE, status, rating, notiz);
  }
}
export class Film extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],) {
    super(mediumId, erstelltAm, titel, MediumTyp.FILM, status, rating, notiz);
  }
}
export class Anime extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    season : number,
    folgen: number,
    currentFolge : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.ANIME, status, rating, notiz);
  }
}
export class Manga extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    band : number,
    kapitel: number,
    currentKapitel : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.MANGA, status, rating, notiz);
  }
}
export class Videospiel extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    platform : Platform) {
    super(mediumId, erstelltAm, titel, MediumTyp.VIDEOSPIEL, status, rating, notiz);
  }
}
export class Buch extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    seiten : number,
    currentSeite : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.BUCH, status, rating, notiz);
  }
}
export class Podcast extends Medium {
  constructor(
    mediumId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    kapitel : number,
    currentKapitel : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.PODCAST, status, rating, notiz);
  }
}
