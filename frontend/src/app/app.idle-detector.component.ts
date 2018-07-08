import {Component} from '@angular/core';
import {DEFAULT_INTERRUPTSOURCES, Idle} from '@ng-idle/core';
import {ENVIRONMENT} from 'environments/environment';
import {AuthService} from './service/auth.service';

@Component({
    selector: 'app-idle-detector',
    template:
            `
        <p-dialog header="Inactivo" [(visible)]="display" [draggable]="false" [closable]="false"
                  [resizable]="false" [closeOnEscape]="true" modal="modal" width="300" responsive="true">
            <p *ngIf="countdown">Su sesión expirará por inactivad en {{countdown}} segundos</p>
            <p *ngIf="!countdown">Su sesión ha expirado</p>
            <footer *ngIf="timeout">
                <div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                    <button type="button" pButton icon="fa-check" (click)="expired()" label="Ok"></button>
                </div>
            </footer>
        </p-dialog>
    `
})
export class IdleDetectorComponent {
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

    private expired() {
        this.authService.logout();
        this.display = false;
    }
}
