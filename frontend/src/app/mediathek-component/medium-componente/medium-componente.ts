import {Component, inject, OnInit, signal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Buch, Film, Manga, Medium, MediumTyp, Podcast, Serie, Videospiel} from '../../domain/medium';
import {MediumErstellenForm} from './medium-erstellen-form/medium-erstellen-form';
import {MediumApiService} from '../../service/api.service';
import {NgForOf} from '@angular/common';


@Component({
  selector: 'app-medium-componente',
  imports: [MediumErstellenForm, NgForOf],
  templateUrl: './medium-componente.html',
  styleUrl: './medium-componente.css'
})
export class MediumComponente implements OnInit{

  openFormModalSignal = signal(false)

  mediumApiService = inject(MediumApiService);
  route = inject(ActivatedRoute);
  mediumAuswahl : string = "";

  mediumTyp! : MediumTyp;
  medien! : Medium[];

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.mediumTyp = params['mediumTyp'];
      this.mediumAuswahl = this.mediumHeader
      this.mediumApiService.getMedienByMediumTyp(this.mediumTyp).subscribe(arr => {
        this.medien = arr.map(m => {
          switch (this.mediumTyp) {
            case MediumTyp.SERIE: return Object.assign(new Serie(), m);
            case MediumTyp.ANIME: return Object.assign(new Serie(), m);
            case MediumTyp.MANGA: return Object.assign(new Manga(), m);
            case MediumTyp.FILM: return Object.assign(new Film(), m);
            case MediumTyp.VIDEOSPIEL: return Object.assign(new Videospiel(), m);
            case MediumTyp.BUCH: return Object.assign(new Buch(), m);
            case MediumTyp.PODCAST: return Object.assign(new Podcast(), m);
            default: return Object.assign(new Medium(), m);
          }
        });
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

  openFormModal() {
    this.openFormModalSignal.update(v => true)
  }
}
