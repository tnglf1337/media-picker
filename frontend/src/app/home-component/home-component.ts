import {Component, inject, OnInit} from '@angular/core';
import {MediumApiService} from '../service/api.service';
import { CommonModule } from '@angular/common';
import {RandomMediumService} from './random-medium.service';
import {StatistikComponent} from './statistik-component/statistik-component';

@Component({
  selector: 'app-home-component',
  imports: [CommonModule, StatistikComponent],
  templateUrl: './home-component.html',
  styleUrls: ['./home-component.css', '../../styles.css']
})
export class HomeComponent implements OnInit {
  apiService : MediumApiService = inject(MediumApiService)
  randomMediumService! : RandomMediumService;
  currentMedien : { [key: string]: any[] } = {}
  randomMedium : any = null;

  ngOnInit(): void {
    this.apiService.getCurrentMedien().subscribe({
      next: data => {
        this.currentMedien = data;
        console.log(this.currentMedien);
        this.randomMediumService = new RandomMediumService(this.currentMedien)
      }
    })
  }

  wuerfelCurrentMedien() {
    this.randomMedium = this.randomMediumService.getRandomMedium();
  }
}
