import {AfterViewInit, Component, CUSTOM_ELEMENTS_SCHEMA, inject, OnInit, signal, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MediumTyp} from '../../domain/medium';
import {MediumErstellenForm} from './medium-erstellen-form/medium-erstellen-form';


@Component({
  selector: 'app-medium-componente',
  imports: [MediumErstellenForm],
  templateUrl: './medium-componente.html',
  styleUrl: './medium-componente.css'
})
export class MediumComponente implements OnInit{

  openFormModalSignal = signal(false)

  route = inject(ActivatedRoute);
  mediumAuswahl : string = "";

  mediumTyp! : MediumTyp;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.mediumTyp = params['mediumTyp'];
      this.mediumAuswahl = this.mediumHeader
      console.log("mediumTyp: ", this.mediumTyp)
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
