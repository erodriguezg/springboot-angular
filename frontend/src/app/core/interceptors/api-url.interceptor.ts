import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ENVIRONMENT } from '../../../environments/environment';


@Injectable({
    providedIn: 'root'
  })
export class ApiUrlInterceptor implements HttpInterceptor {

    private static seDebeAgregarPrefijoApi(req: HttpRequest<any>): boolean {
        return !req.url.startsWith(ENVIRONMENT.API_URL) && !req.url.startsWith('/assets/');
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (ApiUrlInterceptor.seDebeAgregarPrefijoApi(req)) {
            const apiReq = req.clone({url: `${ENVIRONMENT.API_URL}${req.url}`});
            console.log('url: ' + apiReq.url);
            return next.handle(apiReq);
        } else {
            console.log('url: ' + req.url);
            return next.handle(req);
        }
    }

}

