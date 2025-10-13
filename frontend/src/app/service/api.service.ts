import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Anime, Buch, Film, Manga, Medium, Podcast, Serie, Videospiel} from '../domain/medium';

@Injectable({
  providedIn: 'root'
})
export class MediumApiService {

  BASE_URL_ERSTELLEN = "http://localhost:9756/medium-erstellen"

  constructor(private http: HttpClient) { }

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

}
