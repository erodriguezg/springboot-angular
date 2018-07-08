import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {RegionDto} from '../dto/region.dto';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RegionService {

    constructor(private http: HttpClient) {
    }

    traerTodos(): Observable<RegionDto[]> {
        return this.http.get('/regiones')
            .map(resJson => <RegionDto[]> resJson);
    }

}
