import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Anime, Buch, Film, Manga, Medium, MediumTyp, Podcast, Serie, Videospiel} from '../domain/medium';
import {Mediathek} from '../domain/mediathek';

@Injectable({
  providedIn: 'root'
})
export class MediumApiService {
  BASE_URL = "http://localhost:9756"
  BASE_URL_ERSTELLEN = "http://localhost:9756/medium-erstellen"

  constructor(private http: HttpClient) { }

  getMediathek() : Observable<Mediathek> {
    return this.http.get<Mediathek>(this.BASE_URL + '/user-medien')
  }

  postSerie(medium : Serie) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/serie', medium)
  }

  postFilm(medium : Film) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/film', medium)
  }

  postAnime(medium : Anime) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/anime', medium)
  }

  postManga(medium : Manga) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/manga', medium)
  }

  postVideospiel(medium : Videospiel) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/videospiel', medium)
  }
  postBuch(medium : Buch) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/buch', medium)
  }

  postPodcast(medium : Podcast) : Observable<any> {
    console.log("posting new medium: ", medium);
    return this.http.post<any>(this.BASE_URL_ERSTELLEN + '/podcast', medium)
  }

  getMedienByMediumTyp(mediumTyp: MediumTyp) : Observable<Medium[]> {
    return this.http.get<Medium[]>(this.BASE_URL + '/get-medien-by-typ/' + MediumTyp[mediumTyp]);
  }

  deleteMedium(mediumId: string) : Observable<any> {
    return this.http.delete<any>(this.BASE_URL + '/medium-loeschen/' + mediumId);
  }

  sendDecrementMedium(mediumId: string): Observable<any> {
    return this.http.get<any>(this.BASE_URL + '/medium-dekrement/' + mediumId);
  }

  sendIncrementMedium(mediumId: string) {
    return this.http.get<any>(this.BASE_URL + '/medium-inkrement/' + mediumId);
  }

  sendDecrementRating(mediumId: string) {
    return this.http.get<any>(this.BASE_URL + '/medium-rating-dekrement/' + mediumId);
  }

  sendIncrementRating(mediumId: string) {
    return this.http.get<any>(this.BASE_URL + '/medium-rating-inkrement/' + mediumId);
  }
}
