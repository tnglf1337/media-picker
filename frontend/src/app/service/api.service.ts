import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Medium} from '../domain/medium';

@Injectable({
  providedIn: 'root'
})
export class MediumApiService {

  BASE_URL = "http://localhost:9756"

  constructor(private http: HttpClient) { }

  postMedium(medium : Medium) : Observable<any> {
    return this.http.post<any>(this.BASE_URL + '/medium-erstellen', medium)
  }
}
