import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditarComponent } from './editar/editar.component';
import { IndexComponent } from './index/index.component';
import { SharedTemplateModule } from '../shared-template/shared-template.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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


@NgModule({
  declarations: [
    EditarComponent,
    IndexComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedTemplateModule,
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
