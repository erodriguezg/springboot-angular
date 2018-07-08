import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ShowcaseService {

    constructor(private httpService: HttpService) {
    }

    public lanzarErrorNegocio(): Observable<any> {
        return this.httpService.getJson('/showcase/error-negocio');
    }

    public lanzarErrorInterno(): Observable<any> {
        return this.httpService.getJson('/showcase/error-interno');
    }

    public lanzarErrorEntrada(): void {
        // pendiente: mostrar la info de error de org.springframework.validation.BindingResult
    }

    public accederAccionAdministrador(): Observable<any> {
        return this.httpService.getJson('/showcase/accion-admin');
    }

    public procesarEnBackend(): Observable<any> {
        return this.httpService.getJson('/showcase/sleep');
    }

}
