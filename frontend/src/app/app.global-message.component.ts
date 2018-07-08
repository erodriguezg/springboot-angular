import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {GlobalMessageData, GlobalMessageService, SEVERITY} from './service/global-message.service';

@Component({
    selector: 'app-global-message',
    template: `
        <p-dialog [header]="title" [visible]="dialogVisible"
                  [class]="classes"
                  [width]="285"
                  [closable]="false" [modal]="true">

            <div class="message">
                {{message}}
            </div>

            <p-footer>
                <button pButton (click)="cerrarDialog()"
                        class="btn btn-default" title=""
                        label="{{ 'labels.cerrar' | translate }}"></button>
            </p-footer>
        </p-dialog>
    `
})
export class AppGlobalMessageComponent implements OnDestroy {

    readonly BASECLASS: string = 'ui-global-message-dialog';

    title: string;
    message: string;
    classes: string;
    dialogVisible: boolean;
    private onShowSubcription: Subscription;

    constructor(private globalMessageService: GlobalMessageService) {
        this.dialogVisible = false;
        this.onShowSubcription = this.globalMessageService.onShow
            .subscribe(data => this.eventListener(data));
    }

    ngOnDestroy(): void {
        if (this.onShowSubcription) {
            this.onShowSubcription.unsubscribe();
        }
    }

    cerrarDialog(): void {
        this.dialogVisible = false;
    }

    private eventListener(data: GlobalMessageData) {
        this.title = data.title;
        this.message = data.message;
        this.classes = this.loadClasses(data.severity);
        this.dialogVisible = true;
    }

    private loadClasses(severity: SEVERITY) {
        let severityClass = '';
        switch (severity) {
            case SEVERITY.INFO:
                severityClass = 'info';
                break;
            case SEVERITY.ERROR:
                severityClass = 'error';
                break;
        }
        return this.BASECLASS + ' ' + severityClass;
    }


}
