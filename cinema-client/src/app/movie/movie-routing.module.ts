import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MovieDetailsComponent} from './movie-details/movie-details.component';
import {NewComponent} from './new/new.component';

const routes: Routes = [
  {
    path: '',
    component: MovieDetailsComponent,
  },
  {
    path: 'new',
    component: NewComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class MovieRoutingModule { }
