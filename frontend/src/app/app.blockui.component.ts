import {Component, OnDestroy, OnInit} from '@angular/core';
import {BlockuiService} from './service/blockui.service';
import {Subscription} from 'rxjs/Subscription';

@Component({
    selector: 'app-blockui',
    template: `
        <p-dialog header="Procesando..." [(visible)]="dialogVisible"
                  [width]="285" [height]="350"
                  [closable]="false" [modal]="true">
            <img src="assets/images/loading.gif" style="width: 250px"/>
        </p-dialog>
    `
})
export class AppBlockuiComponent implements OnDestroy {
    public dialogVisible: boolean;
    private onShowSubcription: Subscription;
    private onHideSubcription: Subscription;

    constructor(private blockuiService: BlockuiService) {
        this.dialogVisible = false;
        this.onShowSubcription = this.blockuiService.onShow
            .subscribe(() => this.dialogVisible = true);
        this.onHideSubcription = this.blockuiService.onHide
            .subscribe(() => this.dialogVisible = false);
    }

    ngOnDestroy(): void {
        if (this.onShowSubcription) {
            this.onShowSubcription.unsubscribe();
        }
        if (this.onHideSubcription) {
            this.onHideSubcription.unsubscribe();
        }
    }

}
