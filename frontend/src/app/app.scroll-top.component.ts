import {Component, HostListener} from '@angular/core';

@Component({
    selector: 'app-scroll-top',
    template:
    `
        <button *ngIf="show" pButton class="pink-btn btn back-to-top" icon="fa fa-arrow-up" (click)="scrollTo(0, 30)"></button>
    `
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
