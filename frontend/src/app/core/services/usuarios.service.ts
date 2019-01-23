import {Injectable} from '@angular/core';
import {UsuarioFiltroDto} from '../dto/usuario-filtro.dto';
import {Observable} from 'rxjs/Observable';
import {UsuarioDto} from '../dto/usuario.dto';
import {HttpService} from './http.service';

@Injectable({
    providedIn: 'root'
  })
export class UsuariosService {

    constructor(private httpService: HttpService) {
    }

    buscarRowCount(filtros: UsuarioFiltroDto, blockui: boolean = true): Observable<number> {
        return this.httpService.postJsonForText('/usuarios/buscar/rowcount', JSON.stringify(filtros), blockui)
            .map(text => parseInt(text, 10));
    }

    buscar(filtros: UsuarioFiltroDto, inicio: number, fin: number, blockui: boolean = true): Observable<UsuarioDto[]> {
        const params = new URLSearchParams();
        params.set('inicio', inicio.toString());
        params.set('fin', fin.toString());
        return this.httpService.postJson<UsuarioDto[]>('/usuarios/buscar?' + params.toString(), JSON.stringify(filtros), blockui);
    }

    buscarNoPaginado(filtros: UsuarioFiltroDto, blockui: boolean = true): Observable<UsuarioDto[]> {
        return this.httpService.postJson<UsuarioDto[]>('/usuarios/buscar-no-paginado', JSON.stringify(filtros), blockui);
    }

    traerPorId(idUsuario: number, blockui: boolean = true): Observable<UsuarioDto> {
        const urlGet = '/usuarios/id/' + idUsuario;
        return this.httpService.getJson<UsuarioDto>(urlGet, blockui);
    }

    traerPorUsername(username: string, blockui: boolean = true): Observable<UsuarioDto> {
        const urlGet = '/usuarios/username/' + username;
        return this.httpService.getJson<UsuarioDto>(urlGet, blockui);
    }

    traerPorEmail(email: string, blockui: boolean = true): Observable<UsuarioDto> {
        const body = new URLSearchParams();
        body.set('email', email);
        return this.httpService.postFormForJson<UsuarioDto>('/usuarios/email', body, blockui);
    }

    traerPorRun(run: number, blockui: boolean = true): Observable<UsuarioDto> {
        const urlGet = '/usuarios/run/' + run;
        return this.httpService.getJson<UsuarioDto>(urlGet, blockui);
    }

    guardar(usuarioDto: UsuarioDto, blockui: boolean = true): Observable<any> {
        return this.httpService.postJson<any>('/usuarios/guardar', JSON.stringify(usuarioDto), blockui);
    }

    eliminar(usuarioDto: UsuarioDto, blockui: boolean = true): Observable<any> {
        const params = new URLSearchParams();
        params.set('idUsuario', usuarioDto.idPersona.toString());
        return this.httpService.postFormForJson<any>('/usuarios/eliminar', params, blockui);
    }

    cambiarPass(usuarioDto: UsuarioDto, newPass: string, blockui: boolean = true): Observable<any> {
        const params = new URLSearchParams();
        params.set('idUsuario', usuarioDto.idPersona.toString());
        params.set('newPass', newPass);
        return this.httpService.postFormForJson<any>('/usuarios/cambiar-pass', params, blockui);
    }

}
