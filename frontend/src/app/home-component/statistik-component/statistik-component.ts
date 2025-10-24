import {Component, inject, OnInit} from '@angular/core';
import {MediumApiService} from '../../service/api.service';

export interface MediathekStatistik {
  anzahlMedien: number;
  anzahlFilme: number;
  anzahlSerien: number;
  anzahlAnime: number;
  anzahlVideospiele: number;
  anzahlBuecher: number;
  anzahlManga: number;
  anzahlPodcasts: number;
  anzahlFolgenGesehen: number;
  anzahlSeitenGelesen: number;
  anzahlMangaKapitelGelesen: number;
  anzahlPodcastKapitelGehoert: number;
}

@Component({
  selector: 'statistik-component',
  imports: [],
  templateUrl: './statistik-component.html',
  styleUrl: './statistik-component.css'
})
export class StatistikComponent implements OnInit{

  apiService : MediumApiService = inject(MediumApiService)
  mediathekStatistik! : MediathekStatistik;

  ngOnInit(): void {
    this.apiService.getStatistiken().subscribe({
      next: data => {
        this.mediathekStatistik = data;
        console.log("Statistik data loaded: ", this.mediathekStatistik);
      }
    })
  }

}
