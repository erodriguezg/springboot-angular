import {Component, OnInit} from '@angular/core';
import {AppTemplateComponent} from './app.template.component';
import {AuthService} from './service/auth.service';
import {Constantes} from './constantes';
import { IdentidadDto } from './dto/identidad.dto';

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
            this.usuarioConectado = this.obtenerUsuarioConectado(this.authService.getIdentidad());
        }
    }

    private obtenerUsuarioConectado(identidad: IdentidadDto) {
        return `${identidad.nombres} ${identidad.apPaterno} ${identidad.apMaterno}`;
    }

}
