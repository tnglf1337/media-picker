import {Component, inject, OnInit} from '@angular/core';
import {MediumApiService} from '../service/api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-component',
  imports: [CommonModule],
  templateUrl: './home-component.html',
  styleUrls: ['./home-component.css', '../../styles.css']
})
export class HomeComponent implements OnInit {
  apiService : MediumApiService = inject(MediumApiService)
  currentMedien : { [key: string]: any[] } = {}

  ngOnInit(): void {
    this.apiService.getCurrentMedien().subscribe({
      next: data => {
        this.currentMedien = data;
        console.log(this.currentMedien);
      }
    })
  }
}
