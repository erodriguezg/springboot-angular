import {Component, HostListener} from '@angular/core';

@Component({
  selector: 'app-scroll-top',
  templateUrl: './scroll-top.component.html',
  styleUrls: ['./scroll-top.component.css']
})
export class ScrollTopComponent {
  show: boolean;
  constructor() {
      this.show = false;
    }

  @HostListener('window:scroll', ['$event'])
  showScrollButton(event) {
      this.show = document.body.scrollTop !== 0;
  }

  private scrollTo(y, speed) {
      let difference = document.body.scrollTop - y;
      const timer = setInterval(() => {
          const perTick = (difference - speed < 0) ? difference : speed;
          window.scrollBy(0, -perTick);
          difference -= perTick;
          if (difference === 0) {
              clearInterval(timer);
          }
      }, 10);
  }
}
