import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {InicioView} from './view/inicio.view';
import {AppTemplateComponent} from './app.template.component';
import {PermitAllGuard} from './guard/permit-all.guard';
import {AppLogoutComponent} from './app.logout.component';
import {Constantes} from './constantes';
import {GestionarUsuariosView} from './view/gestionar-usuarios.view';
import {LoginView} from './view/login.view';
import {AuthentificatedGuard} from './guard/authentificated.guard';
import {EditarUsuarioView} from './view/editar-usuario.view';
import {ShowcaseView} from './view/showcase.view';

export const routes: Routes = [
    {path: Constantes.ROUTE_LOGIN, component: LoginView, canActivate: [PermitAllGuard]},
    {path: Constantes.ROUTE_LOGOUT, component: AppLogoutComponent, canActivate: [PermitAllGuard]},
    {
        path: Constantes.ROUTE_INICIO, component: AppTemplateComponent, canActivate: [PermitAllGuard],
        children: [
            {path: '', component: InicioView, outlet: 'content'}
        ]
    },
    {
        path: Constantes.ROUTE_SHOWCASE, component: AppTemplateComponent, canActivate: [PermitAllGuard],
        children: [
            {path: '', component: ShowcaseView, outlet: 'content'}
        ]
    },
    {
        path: Constantes.ROUTE_GESTIONAR_USUARIOS, component: AppTemplateComponent, canActivate: [AuthentificatedGuard],
        children: [
            {path: '', component: GestionarUsuariosView, outlet: 'content'}
        ]
    },
    {
        path: Constantes.ROUTE_EDITAR_USUARIO, component: AppTemplateComponent, canActivate: [AuthentificatedGuard],
        children: [
            {path: '', component: EditarUsuarioView, outlet: 'content'}
        ]
    },
    {path: Constantes.ROUTE_EXITO_LOGIN, redirectTo: Constantes.ROUTE_INICIO},
    {path: Constantes.ROUTE_EXITO_LOGOUT, redirectTo: Constantes.ROUTE_INICIO},
    {path: '**', redirectTo: Constantes.ROUTE_INICIO}
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
