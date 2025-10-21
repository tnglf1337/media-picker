import {Pipe, PipeTransform} from '@angular/core';
import {Platform, Status} from './domain/medium';

@Pipe({
  name: 'platformTransform'
})
export class PlatformPipe implements PipeTransform {
  transform(platform: Platform): string {
    switch (platform) {
      case Platform.PC: return 'Pc'
      case Platform.PLAYSTATION_4: return 'Playstation 4'
      case Platform.PLAYSTATION_5: return 'Playstation 5'
      case Platform.PLAYSTATION_6: return 'Playstation 6'
      case Platform.NINTENDO_SWITCH: return 'Nintendo Switch'
      case Platform.NINTENDO_SWITCH_2: return 'Nintendo Switch 2'
      default: return "undefined platform";
    }
  }
}
