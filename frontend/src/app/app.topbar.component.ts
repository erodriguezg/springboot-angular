import {Component, OnInit} from '@angular/core';
import {AppTemplateComponent} from './app.template.component';
import {AuthService} from './service/auth.service';
import {Constantes} from './constantes';
import {UsuarioDto} from './dto/usuario.dto';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnInit {

    rutaLogin: string;
    rutaLogout: string;
    sesionIniciada: boolean;
    usuarioConectado: string;

    constructor(public appTemplate: AppTemplateComponent, public authService: AuthService) {
    }

    ngOnInit(): void {
        this.rutaLogout = Constantes.ROUTE_LOGOUT;
        this.rutaLogin = Constantes.ROUTE_LOGIN;
        this.sesionIniciada = this.authService.isLogged();
        if (this.sesionIniciada) {
            this.usuarioConectado = this.obtenerUsuarioConectado(this.authService.getUsuarioDto());
        }
    }

    private obtenerUsuarioConectado(usuarioDto: UsuarioDto) {
        return usuarioDto.persona.nombres + ' ' + usuarioDto.persona.apPaterno + ' ' + usuarioDto.persona.apMaterno;
    }

}
