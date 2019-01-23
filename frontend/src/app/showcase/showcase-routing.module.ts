
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { TemplateComponent } from '../shared-template/template/template.component';
import { PermitAllGuard } from '../core/guards/permit-all.guard';

const routes: Routes = [
    {
        path: '', component: TemplateComponent, canActivate: [PermitAllGuard],
        children: [
            {path: '', component: IndexComponent, outlet: 'content'}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ShowcaseRoutingModule {}
