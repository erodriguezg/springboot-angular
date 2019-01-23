import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {RegionDto} from '../dto/region.dto';
import {ComunaDto} from '../dto/comuna.dto';
import {HttpService} from './http.service';

@Injectable({
    providedIn: 'root'
  })
export class ComunaService {

    constructor(private httpService: HttpService) {
    }

    traerPorRegion(regionDto: RegionDto): Observable<ComunaDto[]> {
        return this.httpService.getJson<ComunaDto[]>('comunas/region/' + regionDto.idRegion);
    }

}
