import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {BlockuiService} from '../services/blockui.service';

@Injectable({
    providedIn: 'root'
  })
export class BlockuiInterceptor implements HttpInterceptor {

    constructor(private blockuiService: BlockuiService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.tieneParametroBlockui(req)) {
            this.blockuiService.block(1);
            return next.handle(req)
                .finally(() => {
                    this.blockuiService.tick();
                });
        } else {
            return next.handle(req);
        }
    }

    private tieneParametroBlockui(req: HttpRequest<any>): boolean {
        const param: string = req.params.get('blockui');
        if (!param) {
            return false;
        }
        return (param === 'true');
    }
}

