import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Rx';
import {SessionStorage} from 'ngx-webstorage';
import {Sesion} from '../domain/sesion';
import {RespuestaLoginDto} from '../dto/respuesta-login.dto';
import {RefreshTokenDto} from '../dto/refresh-token.dto';
import {Idle} from '@ng-idle/core';
import { ENVIRONMENT } from '../../../environments/environment';
import { CredencialesDto } from '../dto/credenciales.dto';
import { HttpService } from './http.service';
import { IdentidadDto } from '../dto/identidad.dto';

import { Base64 } from 'js-base64';

@Injectable({
    providedIn: 'root'
  })
export class AuthService {

    @SessionStorage()
    public sesion: Sesion;

    private refreshTokenTimer;

    constructor(private httpService: HttpService,
                private router: Router,
                private idle: Idle) {
    }

    /* PUBLICOS */

    getToken(): string {
        return (this.sesion !== undefined && this.sesion != null) ? this.sesion.token : '';
    }

    login(username: string, password: string): Observable<RespuestaLoginDto> {
        const credenciales: CredencialesDto = {
            username: username,
            password: password
        };
        return this.httpService.postJson<RespuestaLoginDto>('/security/login', JSON.stringify(credenciales))
            .map(res => {
                const resp: RespuestaLoginDto = res;
                if (resp.exitoLogin) {
                    this.procesarToken(resp.token);
                }
                return resp;
            });
    }

    refrescarToken(): Observable<RefreshTokenDto> {
        if (!this.isLogged()) {
            return Observable.create(undefined);
        } else {
            return this.httpService.postJson<RefreshTokenDto>('/security/refreshToken', null, false)
                .map(res => {
                    const resp: RefreshTokenDto = res;
                    this.procesarToken(resp.token);
                    return resp;
                });
        }
    }

    logout() {
        this.cleanSession();
        this.router.navigate(['/']);
    }

    logoutNoNavigate() {
        this.cleanSession();
    }

    isLogged(): boolean {
        const logged: boolean = (this.sesion !== undefined && this.sesion !== null);
        if (logged && (this.refreshTokenTimer === undefined || this.refreshTokenTimer == null)) {
            this.setRefreshTokenTimer();
        }
        return logged;
    }

    getIdentidad(): IdentidadDto {
        if (!this.isLogged()) {
            return null;
        }
        return this.sesion.data as IdentidadDto;
    }

    tienePerfil(idPerfil: number): boolean {
        const identidad: IdentidadDto = this.getIdentidad();
        if (!identidad || !identidad.idPerfil) {
            return false;
        }
        return idPerfil === identidad.idPerfil;
    }

    recargarToken(token: string) {
        this.cleanSession();
        this.procesarToken(token);
    }

    /*
    PRIVADOS
     */

    private cleanSession(): void {
        this.sesion = null;
        this.idle.stop();
        this.stopRefreshTokenTimer();
    }

    private procesarToken(token: string) {
        const encodedPayload: string = token.split('.')[1];
        const payload = JSON.parse(Base64.decode(encodedPayload));
        this.sesion = {
            token: token,
            iat: payload.iat,
            exp: payload.exp,
            jti: payload.jti,
            data: JSON.parse(payload.sub)
        };
        this.setRefreshTokenTimer();
        this.idle.watch();
    }

    private setRefreshTokenTimer() {
        const time: number = (this.sesion.exp - (new Date().getTime() / 1000) - ENVIRONMENT.REFRESH_TOKEN_BEFORE_EXP_TIME) * 1000;
        this.refreshTokenTimer = setTimeout(() => this.refrescarToken().subscribe(), time);
    }

    private stopRefreshTokenTimer() {
        clearTimeout(this.refreshTokenTimer);
        this.refreshTokenTimer = null;
    }

}
