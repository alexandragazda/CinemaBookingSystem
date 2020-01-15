import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieDetailsComponent} from './movie-details/movie-details.component';
import {MovieRoutingModule} from './movie-routing.module';
import {MovieService} from './movie-service';
import { NewComponent } from './new/new.component';

@NgModule({
  declarations: [
    MovieDetailsComponent,
    NewComponent,
  ],
  imports: [
    CommonModule,
    MovieRoutingModule
  ],
  providers: [
    [MovieService]]
})
export class MovieModule { }
