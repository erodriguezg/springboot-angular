import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {PerfilDto} from '../dto/perfil.dto';
import {HttpService} from './http.service';

@Injectable()
export class PerfilesService {

    constructor(private httpService: HttpService) {
    }

    traerTodos(): Observable<PerfilDto[]> {
        return this.httpService.getJson('/perfiles/todos');
    }

}
