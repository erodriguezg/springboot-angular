
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
        path: '',
        loadChildren: './home/home.module#HomeModule'
    },
    {
        path: 'showcase',
        loadChildren: './showcase/showcase.module#ShowcaseModule'
    },
    {
        path: 'usuarios',
        loadChildren: './usuarios/usuarios.module#UsuariosModule'
    },
    {path: '**', redirectTo: ''}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
