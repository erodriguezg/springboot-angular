import { Component, OnInit } from '@angular/core';
import { TemplateComponent } from '../template/template.component';
import { AuthService } from '../services/auth.service';
import { ConstantesUtils } from '../utils/constantes.utils';
import { IdentidadDto } from '../dto/identidad.dto';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  rutaLogin: string;
  rutaLogout: string;
  sesionIniciada: boolean;
  usuarioConectado: string;

  constructor(public appTemplate: TemplateComponent, public authService: AuthService) {
  }

  ngOnInit(): void {
      this.rutaLogout = ConstantesUtils.ROUTE_LOGOUT;
      this.rutaLogin = ConstantesUtils.ROUTE_LOGIN;
      this.sesionIniciada = this.authService.isLogged();
      if (this.sesionIniciada) {
          this.usuarioConectado = this.obtenerUsuarioConectado(this.authService.getIdentidad());
      }
  }

  private obtenerUsuarioConectado(identidad: IdentidadDto) {
      return `${identidad.nombres} ${identidad.apPaterno} ${identidad.apMaterno}`;
  }

}
