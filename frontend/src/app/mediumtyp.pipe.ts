import {Pipe, PipeTransform} from '@angular/core';
import {MediumTyp} from './domain/medium';

@Pipe({
  name: 'mediumTypTransform'
})
export class MediumTypPipe implements PipeTransform {
  transform(mediumTyp: string): string {
    switch (mediumTyp) {
      case "SERIE": return 'Serien';
      case "FILM": return 'Filme';
      case "ANIME": return 'Anime';
      case "MANGA": return 'Manga';
      case "VIDEOSPIEL": return 'Videospiele';
      case "BUCH": return 'Bücher';
      case "PODCAST" : return 'Podcasts';
      default: return "undefined medium typ";
    }
  }
}
