import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {GlobalMessageService} from '../service/global-message.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private router: Router, private globalMessageService: GlobalMessageService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req)
            .catch(errorResponse => {
                switch (errorResponse.status) {
                    case 403:
                    case 401:
                        this.router.navigate(['/']);
                        break;
                    case 500:
                        const errorBackend: ErrorBackend = <ErrorBackend>errorResponse.error;
                        if (errorBackend.errorType === 'LogicaNegocioException') {
                            this.globalMessageService.showLogicaNegocioError(errorBackend.message);
                        } else {
                            this.globalMessageService.showErrorInterno();
                        }
                        return Observable.throw(errorBackend);
                }
                let errMsg: string;
                if (errorResponse instanceof HttpErrorResponse) {
                    const err = errorResponse.message || JSON.stringify(errorResponse.error);
                    errMsg = `${errorResponse.status} - ${errorResponse.statusText || ''} Details: ${err}`;
                } else {
                    errMsg = errorResponse.message ? errorResponse.message : errorResponse.toString();
                }
                return Observable.throw(errMsg);
            });
    }
}

class ErrorBackend {
    errorType: string;
    message: string;
    values: any;
}
