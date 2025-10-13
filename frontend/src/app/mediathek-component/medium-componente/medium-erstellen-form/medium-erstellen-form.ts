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
import {Medium, MediumTyp, Platform, Status} from '../../../domain/medium';
import {NgForOf, NgIf} from '@angular/common';
import {MediumFormular} from '../../../domain/form';
import {ReactiveFormsModule} from '@angular/forms';
import {MediumApiService} from '../../../service/api.service';
import { v4 as uuid } from 'uuid';

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
    NgIf
  ],
  templateUrl: './medium-erstellen-form.html',
  styleUrl: './medium-erstellen-form.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MediumErstellenForm implements OnChanges {
  @Input()
  openFormModualSignal!: WritableSignal<boolean>;

  @Input()
  mediumTyp!: MediumTyp;

  @ViewChild('mediumFormModul', { static: false })
  formModal!: ElementRef<BlazeModalElement>;

  apiService = inject(MediumApiService)
  statusOptionen: Status[] = [];
  mediumForm: MediumFormular = {} as MediumFormular;

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
    return Object.values(Platform);
  }

  protected readonly MediumTyp = MediumTyp;

  erstelleMedium() {
    const medium: Medium = this.mediumForm.buildMediumTypEntity();
    console.log("build medium entitiy: ", medium);
    this.apiService.postMedium(medium)
    this.mediumForm.formulareZuruecksetzen()
  }
}
