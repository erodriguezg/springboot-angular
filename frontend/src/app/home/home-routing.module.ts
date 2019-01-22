
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TemplateComponent } from '../shared-template/template/template.component';

import { IndexComponent } from './index/index.component';
import { PermitAllGuard } from 'app/core/guards/permit-all.guard';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
    {
        path: '', component: TemplateComponent, canActivate: [PermitAllGuard],
        children: [
            {path: '', component: IndexComponent, outlet: 'content'}
        ]
    },
    {
        path: 'login', component: LoginComponent, canActivate: [PermitAllGuard]
    },
    {
        path: 'logout', component: LogoutComponent, canActivate: [PermitAllGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {}
