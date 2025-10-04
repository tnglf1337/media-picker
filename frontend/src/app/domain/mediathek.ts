import {Medium} from './medium';

// Aggregate-Root
export class Mediathek {
  constructor(
    private mediathekId : string,
    private userId : string,
    private mediaListe : Medium[]) {
  }
}
