import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';

import { TranslateService } from '@ngx-translate/core';

import { CustomsValidators } from '../../core/validators/customs.validators';
import { RutConverter } from '../../core/converters/rut.converter';
import { ConstantesUtils } from '../../core/utils/constantes.utils';
import { LocaleUtils } from '../../core/utils/locale.utils';
import { FlashScopeService } from '../../core/services/flash-scope.service';
import { UsuariosService } from '../../core/services/usuarios.service';
import { PerfilesService } from '../../core/services/perfiles.service';
import { UsuarioDto } from '../../core/dto/usuario.dto';
import { UsuarioFiltroDto } from '../../core/dto/usuario-filtro.dto';
import { PerfilDto } from '../../core/dto/perfil.dto';

import * as _ from 'lodash';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  msgs = [];
  usuarios: UsuarioDto[];
  formFiltros: FormGroup;
  formPass: FormGroup;
  passDialogVisible: boolean;
  formPassSubmitted: boolean;

  perfilesSelectItems: SelectItem[];
  habilitadosSelectItems: SelectItem[];

  busquedaRowCount: number;
  busquedaTimeout: NodeJS.Timer;
  tablaResultadosVisible: boolean;

  /* calendar */
  minDate: Date;
  maxDate: Date;
  yearRange: string;
  calLocale: any;

  constructor(private usuarioService: UsuariosService,
              private perfilesService: PerfilesService,
              private router: Router,
              private flashScope: FlashScopeService,
              private fb: FormBuilder,
              private confirmationService: ConfirmationService,
              private translate: TranslateService) {
  }

  /* publicos */

  public ngOnInit() {
      this.passDialogVisible = false;
      this.formPassSubmitted = false;
      this.tablaResultadosVisible = false;
      this.initCalendar();
      this.habilitadosSelectItems = ConstantesUtils.createHabilitadosSelectItems();
      this.cargarPerfiles();
      this.crearFormFiltros();
      this.buscar();
  }

  public irNuevoUsuario(): void {
      this.router.navigate([ConstantesUtils.ROUTE_EDITAR_USUARIO]);
  }

  public irEditarUsuario(usuario: UsuarioDto): void {
      this.flashScope.add(ConstantesUtils.FS_KEY_ID_USUARIO, usuario.idPersona);
      this.router.navigate([ConstantesUtils.ROUTE_EDITAR_USUARIO]);
  }

  public loadBusquedaLazy(event: LazyLoadEvent) {
      if (!this.tablaResultadosVisible) {
          return;
      }
      const filtrosDto: UsuarioFiltroDto = this.formFiltrosToDto();
      this.usuarioService.buscar(filtrosDto, event.first, (event.first + event.rows))
          .subscribe(data => {
              console.log('data: ' + data);
              this.usuarios = data;
          });
  }

  public confirmarEliminar(usuarioDto: UsuarioDto): void {
      this.confirmationService.confirm({
          message: this.translate.instant('dialogs.msg.eliminar-usuario'),
          accept: () => {
              console.log('acepta eliminar!');
              this.eliminar(usuarioDto);
          }
      });
  }

  public abrirDialogCambiarPass(usuarioDto: UsuarioDto): void {
      this.crearFormPass(usuarioDto);
      this.passDialogVisible = true;
  }

  public cerrarDialogCambiarPass(): void {
      this.formPass = null;
      this.passDialogVisible = false;
  }

  public aceptarCambiarPass(): void {
      this.formPassSubmitted = true;
      if (!this.formPass.valid) {
          this.msgs.push({severity: 'error',
              summary: this.translate.instant('dialogs.error.title'),
              detail: this.translate.instant('dialogs.msg.form-invalido')});
          return;
      }
      const usuarioDto: UsuarioDto = this.formPass.get('usuarioDto').value;
      const newPass: string = this.formPass.get('password').value;
      this.usuarioService.cambiarPass(usuarioDto, newPass)
          .subscribe(() => {
              this.msgs.push({severity: 'info',
                  summary: this.translate.instant('dialogs.info.title'),
                  detail: this.translate.instant('info.msg.exito')});
              this.cerrarDialogCambiarPass();
          });
  }

  /* privados */

  private formFiltrosToDto(): UsuarioFiltroDto {
      const run = this.formFiltros.get('run').value ? RutConverter.toNumber(this.formFiltros.get('run').value) : null;
      const filtrosDto: UsuarioFiltroDto = this.formFiltros.value;
      filtrosDto.run = run;
      return filtrosDto;
  }

  private buscar(): void {
      if (!this.formFiltros.valid) {
          return;
      }
      clearTimeout(this.busquedaTimeout);
      this.busquedaTimeout = setTimeout(() => {
          const filtrosDto: UsuarioFiltroDto = this.formFiltrosToDto();
          this.usuarioService.buscarRowCount(filtrosDto)
              .subscribe(rowCount => {
                  this.busquedaRowCount = rowCount;
                  this.refrescarTablaResultados();
              });
      }, 600);
  }

  private cargarPerfiles(): void {
      this.perfilesService.traerTodos()
          .subscribe(perfiles => {
              this.perfilesSelectItems = _.concat([ConstantesUtils.createTodosSelectItem()],
                  _.map(perfiles, PerfilDto.perfilToSelectItem));
          });
  }

  private crearFormFiltros(): void {
      this.formFiltros = this.fb.group({
          username: [null,
              Validators.compose([Validators.minLength(4), Validators.maxLength(20), CustomsValidators.validateUsername])],
          perfil: [null],
          habilitado: [null],
          run: [null, CustomsValidators.validateRUN],
          nombres: [null, Validators.compose([Validators.minLength(2), Validators.maxLength(100)])],
          apPaterno: [null, Validators.compose([Validators.minLength(2), Validators.maxLength(100)])],
          apMaterno: [null, Validators.compose([Validators.minLength(2), Validators.maxLength(100)])],
          email: [null, Validators.compose([CustomsValidators.validateEmail, Validators.maxLength(100)])],
          fechaNacimientoInferior: [null],
          fechaNacimientoSuperior: [null],
      });

      // listeners
      this.formFiltros.valueChanges
          .subscribe(val => this.buscar());
  }

  private crearFormPass(dto: UsuarioDto): void {
      this.formPass = this.fb.group({
          usuarioDto: [dto],
          password: [null,
              Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(20)])],
          confirmPassword: [null,
              Validators.required]
      }, {validator: CustomsValidators.matchingPasswords('password', 'confirmPassword')});
      // listeners
      this.formPass.get('password').valueChanges
          .subscribe(val => this.formPass.get('confirmPassword').updateValueAndValidity());
  }

  private initCalendar(): void {
      this.maxDate = new Date();
      this.minDate = new Date();
      this.maxDate.setFullYear(this.maxDate.getFullYear() - 18);
      this.minDate.setFullYear(this.minDate.getFullYear() - 90);
      this.yearRange = this.minDate.getFullYear() + ':' + this.maxDate.getFullYear();
      this.calLocale = LocaleUtils.getCalendarEs();
  }

  private refrescarTablaResultados(): void {
      this.tablaResultadosVisible = false;
      setTimeout(() => this.tablaResultadosVisible = true, 0);
  }

  private eliminar(usuarioDto: UsuarioDto): void {
      this.usuarioService.eliminar(usuarioDto)
          .subscribe(() => {
              this.msgs.push({severity: 'info',
                  summary: this.translate.instant('dialogs.info.title'),
                  detail: this.translate.instant('info.msg.exito')});
              this.buscar();
          });
  }

}
