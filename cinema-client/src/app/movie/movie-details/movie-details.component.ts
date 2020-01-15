import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  private routeSub: Subscription;
  private movieTitle: string;
  private movieID: number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      console.log('title:' + params.title);
      console.log('id:' + params.id);
      this.movieTitle = params.title;
      this.movieID = params.id;
    });
  }

}
