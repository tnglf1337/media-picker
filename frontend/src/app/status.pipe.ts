import {Pipe, PipeTransform} from '@angular/core';
import {Status} from './domain/medium';

@Pipe({
  name: 'statusTransform'
})
export class StatusPipe implements PipeTransform {
  transform(status: Status): string {
    switch (status) {
      case Status.AM_SPIELEN: return 'Am Schauen'
      case Status.AM_HOEREN: return 'Am Hören'
      case Status.AM_LESEN: return 'Am Lesen'
      case Status.AM_SCHAUEN: return 'Am Schauen'
      case Status.ABGEBROCHEN: return 'Abgebrochen'
      case Status.GEPLANT: return 'Geplant'
      case Status.BEENDET: return 'Beendet'
      case Status.PAUSIERT: return 'Pausiert'
      default: return "undefined status";
    }
  }
}
