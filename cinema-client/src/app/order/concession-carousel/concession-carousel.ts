import { Component } from '@angular/core';

@Component({selector: 'app-concession-carousel', templateUrl: './concession-carousel.html'})

export class ConcessionCarouselComponent {
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  // images = ['/assets/img/popcorn-menu.png', '/assets/img/1.jpg'];
}
