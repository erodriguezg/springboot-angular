import {Constantes} from '../constantes';
import {Component, OnInit} from '@angular/core';

import {UsuarioDto} from '../dto/usuario.dto';
import {FlashScopeService} from '../service/flash-scope.service';
import {UsuariosService} from '../service/usuarios.service';
import {Router} from '@angular/router';

import {ConfirmationService, Message, SelectItem} from 'primeng/api';
import {AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {PerfilDto} from '../dto/perfil.dto';
import {PersonaDto} from '../dto/persona.dto';

import {Locale} from '../locale';
import {PerfilesService} from '../service/perfiles.service';
import {CustomsValidators} from '../validators/customs.validators';
import {RutConverter} from '../converters/rut.converter';

const moment = require('moment');
const _ = require('lodash');

@Component({
    templateUrl: 'editar-usuario.view.html'
})
// tslint:disable-next-line:component-class-suffix
export class EditarUsuarioView implements OnInit {

    msgs: Message[];
    modoEditar: boolean;
    usuarioEntrada: UsuarioDto;
    formUsuario: FormGroup;
    submitted: boolean;
    exitoDialogoVisible: boolean;
    perfilesSelectItems: SelectItem[];

    /* calendar */
    minDate: Date;
    maxDate: Date;
    yearRange: string;
    calLocale: any;

    /* aux */
    usernameTimeOut: NodeJS.Timer;
    emailTimeOut: NodeJS.Timer;
    runTimeOut: NodeJS.Timer;

    constructor(private usuarioService: UsuariosService,
                private perfilesService: PerfilesService,
                private flashScope: FlashScopeService,
                private router: Router,
                private confirmationService: ConfirmationService,
                private fb: FormBuilder) {
    }

    ngOnInit() {
        console.log('iniciando modulo edicion-creacion usuario');
        this.exitoDialogoVisible = false;
        this.initCalendar();
        const idUsuarioParam: number = this.flashScope.get(Constantes.FS_KEY_ID_USUARIO);
        if (idUsuarioParam) {
            this.usuarioService.traerPorId(idUsuarioParam)
                .subscribe(data => {
                    if (data) {
                        this.iniciarModoEditar(data);
                    } else {
                        this.iniciarModoCrear();
                    }
                });
        } else {
            this.iniciarModoCrear();
        }

        this.perfilesService.traerTodos()
            .subscribe(perfiles => {
                this.perfilesSelectItems = _.concat([Constantes.createSeleccionarSelectItem()],
                    _.map(perfiles, PerfilDto.perfilToSelectItem));
            });

    }

    confirmAceptar(): void {
        console.log('entre confirmAceptar');
        this.submitted = true;
        if (!this.formUsuario.valid) {
            return;
        }
        this.confirmationService.confirm({
            message: 'Está seguro que desea guardar el usuario?',
            accept: () => {
                console.log('acepta guardar!');
                this.guardar();
            }
        });
    }


    volver(): void {
        this.router.navigate([Constantes.ROUTE_GESTIONAR_USUARIOS]);
    }

    /* privados */

    private iniciarModoCrear(): void {
        this.modoEditar = false;
        this.usuarioEntrada = <UsuarioDto>{};
        this.usuarioEntrada.perfil = null;
        this.usuarioEntrada.persona = <PersonaDto>{};
        this.crearFormulario();
    }

    private iniciarModoEditar(usuario: UsuarioDto): void {
        this.usuarioEntrada = usuario;
        this.modoEditar = true;
        this.crearFormulario();
    }

    private crearFormulario(): void {
        const dto: UsuarioDto = this.usuarioEntrada;

        const extra: any = {validator: CustomsValidators.matchingPasswords('password', 'confirmPassword')};

        this.formUsuario = this.fb.group({
            id: [dto.id],
            habilitado: [dto.habilitado],
            username: [dto.username,
                Validators.compose([Validators.required, Validators.minLength(4),
                    Validators.maxLength(20), CustomsValidators.validateUsername]), this.usernameUnicoValidator()],
            perfil: [dto.perfil,
                Validators.required],
            run: [dto.persona.run ? RutConverter.toString(dto.persona.run) : null,
                Validators.compose([Validators.required, CustomsValidators.validateRUN]), this.runUnicoValidator()],
            nombres: [dto.persona.nombres,
                Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(100)])],
            apPaterno: [dto.persona.apPaterno,
                Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(100)])],
            apMaterno: [dto.persona.apMaterno,
                Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(100)])],
            email: [dto.persona.email,
                Validators.compose([Validators.required, CustomsValidators.validateEmail, Validators.maxLength(100)]),
                this.emailUnicoValidator()],
            fechaNacimiento: [dto.persona.fechaNacimiento ? moment(dto.persona.fechaNacimiento).toDate() : null,
                Validators.required]
        });
        // si es modo editar
        if (!this.modoEditar) {
            this.formUsuario.addControl('password', this.fb.control(null, Validators.compose([Validators.required, Validators.minLength(8),
                Validators.maxLength(20)])));
            this.formUsuario.addControl('confirmPassword', this.fb.control(null, Validators.required));
            this.formUsuario.setValidators(<ValidatorFn>CustomsValidators.matchingPasswords('password', 'confirmPassword'));
            // listeners
            this.formUsuario.get('password').valueChanges
                .subscribe(val => this.formUsuario.get('confirmPassword').updateValueAndValidity());
        }
    }

    private initCalendar(): void {
        const present: Date = new Date();
        this.maxDate = new Date();
        this.minDate = new Date();
        this.maxDate.setFullYear(this.maxDate.getFullYear() - 18);
        this.minDate.setFullYear(this.minDate.getFullYear() - 90);
        this.yearRange = this.minDate.getFullYear() + ':' + this.maxDate.getFullYear();
        this.calLocale = Locale.getCalendarEs();
    }

    private guardar(): void {
        console.log('entre guardar');
        const usuarioDto: UsuarioDto = this.formToDto();
        this.usuarioService.guardar(usuarioDto)
            .subscribe(() => {
                console.log('éxito guardar!');
                this.exitoDialogoVisible = true;
            });
    }

    private formToDto(): UsuarioDto {
        const usuarioDto: UsuarioDto = <UsuarioDto>{};
        usuarioDto.persona = <PersonaDto>{};
        usuarioDto.id = this.formUsuario.get('id').value;
        usuarioDto.username = this.formUsuario.get('username').value;
        usuarioDto.perfil = this.formUsuario.get('perfil').value;
        usuarioDto.habilitado = this.formUsuario.get('habilitado').value;
        usuarioDto.password = this.modoEditar ? this.usuarioEntrada.password : this.formUsuario.get('password').value;
        usuarioDto.persona.fechaNacimiento = this.formUsuario.get('fechaNacimiento').value;
        usuarioDto.persona.email = this.formUsuario.get('email').value;
        usuarioDto.persona.apPaterno = this.formUsuario.get('apPaterno').value;
        usuarioDto.persona.apMaterno = this.formUsuario.get('apMaterno').value;
        usuarioDto.persona.nombres = this.formUsuario.get('nombres').value;
        usuarioDto.persona.run = RutConverter.toNumber(this.formUsuario.get('run').value);
        return usuarioDto;
    }

    private usernameUnicoValidator(): AsyncValidatorFn {
        return (control: AbstractControl): Promise<any> => {
            clearTimeout(this.usernameTimeOut);
            return new Promise<any>(resolve => {
                this.usernameTimeOut = setTimeout(() => {
                    this.usuarioService.traerPorUsername(control.value, false)
                        .subscribe(user => {
                            if (user && user.id !== this.usuarioEntrada.id) {
                                resolve({usernameUnico: true});
                            } else {
                                resolve(null);
                            }
                        }, error => {
                            resolve({usernameUnico: true});
                        });
                }, 1000);
            });
        };
    }

    private emailUnicoValidator(): AsyncValidatorFn {
        return (control: AbstractControl): Promise<any> => {
            clearTimeout(this.emailTimeOut);
            return new Promise<any>(resolve => {
                this.emailTimeOut = setTimeout(() => {
                    this.usuarioService.traerPorEmail(control.value, false)
                        .subscribe(user => {
                            if (user && user.id !== this.usuarioEntrada.id) {
                                resolve({emailUnico: true});
                            } else {
                                resolve(null);
                            }
                        }, error => {
                            resolve({emailUnico: true});
                        });
                }, 1000);
            });
        };
    }

    private runUnicoValidator(): AsyncValidatorFn {
        return (control: AbstractControl): Promise<any> => {
            clearTimeout(this.runTimeOut);
            return new Promise<any>(resolve => {
                this.runTimeOut = setTimeout(() => {
                    this.usuarioService.traerPorRun(RutConverter.toNumber(control.value), false)
                        .subscribe(user => {
                            if (user && user.id !== this.usuarioEntrada.id) {
                                resolve({runUnico: true});
                            } else {
                                resolve(null);
                            }
                        }, error => {
                            resolve({runUnico: true});
                        });
                }, 1000);
            });
        };
    }

}
