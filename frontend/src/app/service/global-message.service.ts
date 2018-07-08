import {EventEmitter, Injectable} from '@angular/core';

@Injectable()
export class GlobalMessageService {

    public onShow: EventEmitter<GlobalMessageData>;

    constructor() {
        this.onShow = new EventEmitter();
    }

    public showErrorInterno(title?: string, message?: string): void {
        if (!title) {
            title = 'Error'; // deberia sacarse del i18n
        }
        if (!message) {
            message = 'Error Interno'; // deberia sacarse del i18n
        }
        this.showError(title, message);
    }

    public showLogicaNegocioError(message: string, title?: string) {
        if (!title) {
            title = 'Información Importante'; // deberia sacarse del i18n
        }
        this.showError(title, message);
    }

    public showError(title: string, message: string) {
        const data: GlobalMessageData = <GlobalMessageData>{
            title: title,
            message: message,
            severity: SEVERITY.ERROR
        };
        this.onShow.emit(data);
    }

}

export interface GlobalMessageData {
    title: string;
    message: string;
    severity: SEVERITY;
}

export enum SEVERITY {INFO, ERROR}
