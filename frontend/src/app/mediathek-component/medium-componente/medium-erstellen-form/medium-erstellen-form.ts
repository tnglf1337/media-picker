import {
  Component,
  CUSTOM_ELEMENTS_SCHEMA,
  effect,
  ElementRef, inject,
  Input,
  OnChanges,
  SimpleChanges,
  ViewChild,
  WritableSignal
} from '@angular/core';
import {Anime, Buch, Film, Manga, MediumTyp, Platform, Podcast, Serie, Status, Videospiel
} from '../../../domain/medium';
import {NgForOf, NgIf} from '@angular/common';
import {MediumFormular} from '../../../domain/form';
import {ReactiveFormsModule} from '@angular/forms';
import {MediumApiService} from '../../../service/api.service';
import {StatusPipe} from '../../../status.pipe';
import {PlatformPipe} from '../../../platform.pipe';

interface BlazeModalElement extends HTMLElement {
  show: () => void;
  close: () => void;
  isOpen: () => Promise<boolean>;
}

@Component({
  selector: 'app-medium-erstellen-form',
  imports: [
    NgForOf,
    ReactiveFormsModule,
    NgIf,
    StatusPipe,
    PlatformPipe
  ],
  templateUrl: './medium-erstellen-form.html',
  styleUrl: './medium-erstellen-form.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MediumErstellenForm implements OnChanges {
  @Input()
  openFormModualSignal!: WritableSignal<boolean>;

  @Input()
  mediumErstelltSignal!: WritableSignal<boolean>;

  @Input()
  mediumTyp!: MediumTyp;

  @ViewChild('mediumFormModul', { static: false })
  formModal!: ElementRef<BlazeModalElement>;

  apiService = inject(MediumApiService)
  statusOptionen: Status[] = [];
  mediumForm: MediumFormular = {} as MediumFormular;
  internalServerError : boolean = false;

  constructor() {
    effect(() => {
      if (this.openFormModualSignal()) this.openFormModal();
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    // Prüfen ob mediumTyp verfügbar ist UND sich geändert hat
    if (changes['mediumTyp'] && this.mediumTyp !== undefined && this.mediumTyp !== null) {
      this.initializeComponent();
    }
  }

  private initializeComponent() {
    this.mediumForm = new MediumFormular(this.mediumTyp);

    this.statusOptionen = [];

    if(this.mediumTyp == MediumTyp.SERIE || this.mediumTyp == MediumTyp.FILM || this.mediumTyp == MediumTyp.ANIME) {
      this.statusOptionen.push(Status.AM_SCHAUEN);
    } else if(this.mediumTyp == MediumTyp.BUCH || this.mediumTyp == MediumTyp.MANGA) {
      this.statusOptionen.push(Status.AM_LESEN);
    } else if(this.mediumTyp == MediumTyp.VIDEOSPIEL) {
      this.statusOptionen.push(Status.AM_SPIELEN);
    } else if(this.mediumTyp == MediumTyp.PODCAST){
      this.statusOptionen.push(Status.AM_HOEREN);
    }

    this.statusOptionen.push(
      Status.PAUSIERT,
      Status.BEENDET,
      Status.GEPLANT,
      Status.ABGEBROCHEN
    );

    this.mediumForm.printForm()
  }

  get modalHeader(): string {
    const headers: Record<MediumTyp, string> = {
      [MediumTyp.SERIE]: 'Serie',
      [MediumTyp.FILM]: 'Film',
      [MediumTyp.ANIME]: 'Anime',
      [MediumTyp.MANGA]: 'Manga',
      [MediumTyp.VIDEOSPIEL]: 'Videospiel',
      [MediumTyp.BUCH]: 'Buch',
      [MediumTyp.PODCAST]: 'Podcast'
    };

    return headers[this.mediumTyp] ?? 'Medien';
  }

  openFormModal() {
    // Sicherstellen, dass die Komponente initialisiert ist bevor Modal geöffnet wird
    if (this.formModal?.nativeElement) {
      this.formModal.nativeElement.show();
    }
  }

  closeFormModal() {
    if (this.formModal?.nativeElement) {
      this.formModal.nativeElement.close();
    }
    this.openFormModualSignal.update(v => false);
  }

  get platforms() {
    return [Platform.PC, Platform.PLAYSTATION_4, Platform.PLAYSTATION_5, Platform.PLAYSTATION_6, Platform.NINTENDO_SWITCH, Platform.NINTENDO_SWITCH_2];
  }

  protected readonly MediumTyp = MediumTyp;

  erstelleMedium() {
    const medium: Serie | Film | Anime | Manga | Videospiel | Buch | Podcast = this.mediumForm.buildMediumTypEntity();

    if (medium instanceof Serie) {
      this.apiService.postSerie(medium).subscribe({
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    } else if (medium instanceof Film) {
      this.apiService.postFilm(medium).subscribe( {
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
      this.mediumErstelltSignal.set(true);
    } else if (medium instanceof Anime) {
      this.apiService.postAnime(medium).subscribe({
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    } else if (medium instanceof Manga) {
      this.apiService.postManga(medium).subscribe({
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    } else if (medium instanceof Videospiel) {
      this.apiService.postVideospiel(medium).subscribe({
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    } else if (medium instanceof Buch) {
      this.apiService.postBuch(medium).subscribe({
        next: () => {
        this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    } else if (medium instanceof Podcast) {
      this.apiService.postPodcast(medium).subscribe({
        next: () => {
          this.mediumErstelltSignal.set(true);
        },
        error: status => {
          this.internalServerError = true;
          console.error('Fehler beim Erstellen des Mediums:', status);
        }
      });
    }

    this.mediumForm.formulareZuruecksetzen()
  }
}
