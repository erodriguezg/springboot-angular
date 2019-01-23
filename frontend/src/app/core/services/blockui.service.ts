import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class BlockuiService {
    public onShow: EventEmitter<void>;
    public onHide: EventEmitter<void>;
    private ticks: number;

    constructor() {
        this.ticks = 0;
        this.onShow = new EventEmitter();
        this.onHide = new EventEmitter();
    }

    block(ticks: number): void {
        if (ticks > 0) {
            if (this.ticks === 0) {
                this.onShow.emit();
            }
            this.ticks += ticks;
        }
    }

    tick(): void {
        this.ticks -= 1;
        if (this.ticks <= 0) {
            this.ticks = 0;
            this.onHide.emit();
        }
    }
}
