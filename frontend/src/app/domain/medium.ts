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
  PLAYSTATION_4= "PLAYSTATION_4",
  PLAYSTATION_5 = "PLAYSTATION_5",
  PLAYSTATION_6 = "PLAYSTATION_6",
  PC = "PC",
  NINTENDO_SWITCH = "NINTENDO_SWITCH",
  NINTENDO_SWITCH_2 = "NINTENDO_SWITCH_2",
}

//TODO getter schreiben

// ##### KLASSEN #####
export class Medium {
  constructor(
    private mediumId? : string | null,
    private erstelltAm? : string,
    private titel? : string,
    private mediumTyp? : MediumTyp,
    private status? : Status,
    private rating? : number,
    private notiz? : string[]
    ) {
  }
}

export class Serie extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],
    public season? : number,
    public folgen?: number,
    public currentFolge? : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.SERIE, status, rating, notiz);
  }
}
export class Film extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],) {
    super(mediumId, erstelltAm, titel, MediumTyp.FILM, status, rating, notiz);
  }
}
export class Anime extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],
    public season? : number,
    public folgen?: number,
    public currentFolge? : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.ANIME, status, rating, notiz);
  }
}
export class Manga extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],
    public band? : number,
    public kapitel?: number,
    public currentKapitel? : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.MANGA, status, rating, notiz);
  }
}
export class Videospiel extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],
    public platform? : Platform) {
    super(mediumId, erstelltAm, titel, MediumTyp.VIDEOSPIEL, status, rating, notiz);
  }
}
export class Buch extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status? : Status,
    rating? : number,
    notiz? : string[],
    public seiten? : number,
    public currentSeite? : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.BUCH, status, rating, notiz);
  }
}
export class Podcast extends Medium {
  constructor(
    mediumId? : string | null,
    erstelltAm? : string,
    titel? : string,
    status?: Status,
    rating? : number,
    notiz? : string[],
    public kapitel? : number,
    public currentKapitel? : number) {
    super(mediumId, erstelltAm, titel, MediumTyp.PODCAST, status, rating, notiz);
  }
}
