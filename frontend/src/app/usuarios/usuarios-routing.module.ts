
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TemplateComponent } from '../shared-template/template/template.component';
import { AuthentificatedGuard } from '../core/guards/authentificated.guard';

import { IndexComponent } from './index/index.component';
import { EditarComponent } from './editar/editar.component';

const routes: Routes = [
    {
        path: '', component: TemplateComponent, canActivate: [AuthentificatedGuard],
        children: [
            {path: '', component: IndexComponent, outlet: 'content'}
        ]
    },
    {
        path: 'editar', component: TemplateComponent, canActivate: [AuthentificatedGuard],
        children: [
            {path: '', component: EditarComponent, outlet: 'content'}
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UsuariosRoutingModule {}
