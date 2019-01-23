import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { TranslateModule } from '@ngx-translate/core';

import { SharedTemplateModule } from '../shared-template/shared-template.module';
import { SharedCommonsModule } from '../shared-commons/shared-commons.module';

import { EditarComponent } from './editar/editar.component';
import { IndexComponent } from './index/index.component';

/* primeng */
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { GrowlModule } from 'primeng/components/growl/growl';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { UsuariosRoutingModule } from './usuarios-routing.module';


@NgModule({
  declarations: [
    EditarComponent,
    IndexComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    SharedCommonsModule,
    SharedTemplateModule,
    UsuariosRoutingModule,
    /*primeng*/
    ButtonModule,
    CalendarModule,
    ConfirmDialogModule,
    DataTableModule,
    DialogModule,
    DropdownModule,
    GrowlModule,
    InputTextModule,
    TooltipModule
  ]
})
export class UsuariosModule { }
