import { Component, OnInit } from '@angular/core';
import { DEFAULT_INTERRUPTSOURCES, Idle } from '@ng-idle/core';
import { ENVIRONMENT } from 'environments/environment';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-idle-detector',
  templateUrl: './idle-detector.component.html',
  styleUrls: ['./idle-detector.component.css']
})
export class IdleDetectorComponent implements OnInit {
    display: boolean;
    countdown: number;
    timeout: boolean;

    constructor(private idle: Idle, private authService: AuthService) {
        this.display = false;
        this.timeout = false;
        this.idle.setIdle(ENVIRONMENT.IDLE_TIME);
        this.idle.setTimeout(ENVIRONMENT.IDLE_TIMEOUT_TIME);
        this.idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

        this.idle.onIdleEnd.subscribe(() => this.display = false);
        this.idle.onTimeoutWarning.subscribe((countdown) => {
            this.display = true;
            this.countdown = countdown;
        });
        this.idle.onTimeout.subscribe(() => {
            this.countdown = undefined;
            this.timeout = true;
            this.authService.sesion = null;
        });
        if (this.authService.isLogged()) {
            this.idle.watch();
        }
    }

    ngOnInit() { }

    private expired() {
        this.authService.logout();
        this.display = false;
    }

}
