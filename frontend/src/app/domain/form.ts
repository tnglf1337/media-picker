import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Anime, Buch, Film, Manga, Medium, MediumTyp, Podcast, Serie, Status, Videospiel} from './medium';
import { v4 as uuid } from 'uuid';

export class MediumFormular {

  private primärForm : FormGroup = {} as FormGroup;
  private sekundärForm: FormGroup = {} as FormGroup;

  constructor(private mediumTyp: MediumTyp) {
    this.initPrimaerForm()
    this.initSekundaerForm()
  }

  initPrimaerForm() {
    this.primärForm = new FormGroup({
        titel: new FormControl(null, [Validators.required]),
        status: new FormControl(Status.GEPLANT, [Validators.required])
    })
  }

  initSekundaerForm() {
    console.log("mediumTyp ModulFormular: ", this.mediumTyp);
    if(this.mediumTyp == MediumTyp.SERIE || this.mediumTyp == MediumTyp.ANIME) {
      this.sekundärForm = new FormGroup({
        season: new FormControl(null, [Validators.required]),
        folgen: new FormControl(null, [Validators.required]),
        currentFolge: new FormControl(null)
      })
    } else if(this.mediumTyp == MediumTyp.MANGA) {
      this.sekundärForm = new FormGroup({
        band: new FormControl(null, [Validators.required]),
        kapitel: new FormControl(null, [Validators.required]),
        currentKapitel: new FormControl(null)
      })
    } else if(this.mediumTyp == MediumTyp.VIDEOSPIEL) {
      this.sekundärForm = new FormGroup({
        platform: new FormControl(null, [Validators.required]),
      })
    } else if(this.mediumTyp == MediumTyp.BUCH) {
      this.sekundärForm = new FormGroup({
        seiten: new FormControl(null, [Validators.required]),
        currentSeite: new FormControl(null)
      })
    } else if(this.mediumTyp == MediumTyp.PODCAST) {
      this.sekundärForm = new FormGroup({
        kapitel: new FormControl(null, [Validators.required]),
        currentKapitel: new FormControl(null)
      })
    }
  }

  getPrimaerForm(): FormGroup {
    return this.primärForm
  }

  getSekundaerForm() : FormGroup {
    return this.sekundärForm
  }

  formulareZuruecksetzen() : void {
    this.primärForm.reset()
    this.sekundärForm.reset()
  }

  buildMediumTypEntity(): Serie | Film | Anime | Manga | Videospiel | Buch | Podcast {
    let medium!: Serie | Film | Anime | Manga | Videospiel | Buch | Podcast;

    const erstelltAm = new Date().toISOString()

    if(this.mediumTyp == MediumTyp.SERIE) {
      this.printForm()
      medium =  new Serie(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("season")?.value,
        this.sekundärForm.get("folgen")?.value,
        this.sekundärForm.get("currentFolge")?.value
      )
    } else if(this.mediumTyp == MediumTyp.FILM) {
      medium = new Film(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        []
      )
    } else if(this.mediumTyp == MediumTyp.ANIME) {
      medium = new Anime(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("season")?.value,
        this.sekundärForm.get("folgen")?.value,
        this.sekundärForm.get("currentFolge")?.value
      )
    } else if(this.mediumTyp == MediumTyp.MANGA) {
      medium = new Manga(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("band")?.value,
        this.sekundärForm.get("kapitel")?.value,
        this.sekundärForm.get("currentKapitel")?.value
      )
    } else if(this.mediumTyp == MediumTyp.VIDEOSPIEL) {
      medium = new Videospiel(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("platform")?.value
      )
    } else if(this.mediumTyp == MediumTyp.BUCH) {
      medium = new Buch(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("seiten")?.value,
        this.sekundärForm.get("currentSeite")?.value
      )
    } else if(this.mediumTyp == MediumTyp.PODCAST) {
      medium = new Podcast(
        null,
        erstelltAm,
        this.primärForm.get("titel")?.value,
        this.primärForm.get("status")?.value,
        0,
        [],
        this.sekundärForm.get("kapitel")?.value,
        this.sekundärForm.get("currentKapitel")?.value
      )
      return medium;
    }

    return medium;
  }

  printForm() : void {
    console.log(this.primärForm.value)
    console.log(this.sekundärForm.value)
  }
}
