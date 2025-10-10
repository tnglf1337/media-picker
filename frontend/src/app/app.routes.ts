import { Routes } from '@angular/router';
import {HomeComponent} from './home-component/home-component';
import {MediumComponente} from './mediathek-component/medium-componente/medium-componente';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'uebersicht',
    pathMatch: 'full'
  },
  {
    path: "uebersicht",
    component: HomeComponent,
  },
  {
    path: "medium",
    children: [
      {
        path: ":mediumTyp",
        component: MediumComponente
      }
    ]
  }
];
