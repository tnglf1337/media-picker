// ##### ENUMS #####
export enum MediaTyp {
  SERIE, FILM, ANIME, MANGA, VIDEOSPIEL, BUCH, PODCAST
}
export enum Status {
  BEENDET = "beendet",
  AM_SCHAUEN = "am Schauen",
  AM_SPIELEN = "am Spielen",
  AM_LESEN = "am Lesen",
  AM_HOEREN = "am Hören",
  GEPLANT = "geplant",
  PAUSIERT = "pausiert",
  ABGEBROCHEN = "abgebrochen",
}
export enum Platform {
  PLAYSTATION_4= "ps4",
  PLAYSTATION_5 = "ps5",
  PLAYSTATION_6 = "ps6",
  PC = "px",
  NINTENDO_SWITCH = "ns1",
  NINTENDO_SWITCH_2 = "ns2"
}

// ##### KLASSEN #####
export class Medium {
  constructor(
    private mediumId : string,
    private mediathekId : string,
    private erstelltAm : string,
    private titel : string,
    private mediaType : MediaTyp,
    private status : Status,
    private rating : number,
    private notiz : string[]
    ) {
  }
}

export class Serie extends Medium {
  constructor(
    mediumId : string,
    userId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    season : number,
    folgen: number,
    currentFolge : number) {
    super(mediumId, userId, erstelltAm, titel, MediaTyp.SERIE, status, rating, notiz);
  }
}
export class Film extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.FILM, status, rating, notiz);
  }
}
export class Anime extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    season : number,
    folgen: number,
    currentFolge : number) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.ANIME, status, rating, notiz);
  }
}
export class Manga extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    band : number,
    kapitel: number,
    currentKapitel : number) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.MANGA, status, rating, notiz);
  }
}
export class Videospiel extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    platform : Platform) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.VIDEOSPIEL, status, rating, notiz);
  }
}
export class Buch extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    seiten : number,
    currentSeite : number) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.BUCH, status, rating, notiz);
  }
}
export class Podcast extends Medium {
  constructor(
    mediumId : string,
    mediathekId : string,
    erstelltAm : string,
    titel : string,
    status : Status,
    rating : number,
    notiz : string[],
    kapitel : number,
    currentKapitel : number) {
    super(mediumId, mediathekId, erstelltAm, titel, MediaTyp.PODCAST, status, rating, notiz);
  }
}
