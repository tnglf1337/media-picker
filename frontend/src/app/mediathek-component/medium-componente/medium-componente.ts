import {Component, effect, inject, OnInit, signal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MediumTyp, Status} from '../../domain/medium';
import {MediumErstellenForm} from './medium-erstellen-form/medium-erstellen-form';
import {MediumApiService} from '../../service/api.service';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {StatusPipe} from '../../status.pipe';


@Component({
  selector: 'app-medium-componente',
  imports: [MediumErstellenForm, NgForOf, NgIf, DatePipe, StatusPipe],
  templateUrl: './medium-componente.html',
  styleUrl: './medium-componente.css'
})
export class MediumComponente implements OnInit{

  openFormModalSignal = signal(false)
  mediumErstelltSignal = signal(false)

  mediumApiService = inject(MediumApiService);
  route = inject(ActivatedRoute);
  mediumAuswahl : string = "";

  mediumTyp! : MediumTyp;
  medien! : any[];

  constructor() {
    effect(() => {
      if (this.mediumErstelltSignal()) {
        this.ngOnInit()
        this.mediumErstelltSignal.set(false)
      }
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.mediumTyp = params['mediumTyp'];
      this.mediumAuswahl = this.mediumHeader
      this.mediumApiService.getMedienByMediumTyp(this.mediumTyp).subscribe(arr => {
        this.medien = arr
      });
    });
  }

  get mediumHeader(): string {
    const headers: Record<MediumTyp, string> = {
      [MediumTyp.SERIE]: 'Serien',
      [MediumTyp.FILM]: 'Filme',
      [MediumTyp.ANIME]: 'Anime',
      [MediumTyp.MANGA]: 'Manga',
      [MediumTyp.VIDEOSPIEL]: 'Videospiele',
      [MediumTyp.BUCH]: 'Bücher',
      [MediumTyp.PODCAST]: 'Podcasts'
    };

    return headers[this.mediumTyp] ?? 'Medien';
  }

  medienFilteredByStatus(status : string | Status) {
    return this.medien.filter(medien => medien.status === status)
  }

  mediumLoeschen(mediumId : string) {
    this.mediumApiService.deleteMedium(mediumId).subscribe({
      next: res => {
        console.log("Medium erfolgreich gelöscht")
        this.ngOnInit()
      }
    });
  }

  openFormModal() {
    this.openFormModalSignal.update(v => true)
  }

  protected readonly MediumTyp = MediumTyp;
  protected readonly Object = Object;
  protected readonly Status = Status;

  dekrementMedium(mediumId: string) {
    this.mediumApiService.sendDecrementMedium(mediumId).subscribe({
      next: () => {
        this.ngOnInit()
      }
    });
  }

  inkrementMedium(mediumId: string) {
    this.mediumApiService.sendIncrementMedium(mediumId).subscribe({
      next: () => {
        this.ngOnInit()
      }
    });
  }
}
