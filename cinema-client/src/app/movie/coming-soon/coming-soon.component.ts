import {Component, OnInit} from '@angular/core';
import {MovieDTO} from '../../entities/MovieDTO';
import {MovieService} from '../movie-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-coming-soon',
  templateUrl: './coming-soon.component.html',
  styleUrls: ['./coming-soon.component.css']
})
export class ComingSoonComponent implements OnInit {

  movieDTOs = new Array<MovieDTO>();
  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  months = new Array<number>();
  month: string;
  today = new Date();

  constructor(private movieService: MovieService, private router: Router, private route: ActivatedRoute) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false; // reload the page when route params change
  }

  initMonths() {
    const nrMonths = 4;
    let i;
    for (i = 0; i < nrMonths; i++) {
     this.months.push(this.today.getMonth() + i + 1);
    }
  }

  ngOnInit() {
    this.initMonths();

    this.route.queryParams.subscribe(params => {
      this.month = params.month;
    });

    let monthParam;
    if (this.month === 'all') {
      monthParam = -1;
    } else {
      monthParam = this.month;
    }

    if (this.month === 'all') {
      (document.getElementById('months') as HTMLSelectElement).options.selectedIndex = 0;
    } else {
      let i;
      for (i = 0; i < this.months.length; i++) {
        if (this.months[i].toString() === this.month) {
          (document.getElementById('months') as HTMLSelectElement).options.selectedIndex = i + 1;
          break;
        }
      }
    }

    return this.movieService.comingSoon(monthParam)
      .subscribe(data => {
        this.movieDTOs = data;
      });
  }

  selectMonth(value: string) {
    this.router.navigate(['/movies/coming-soon'], {queryParams: {month : value }});
  }
}
