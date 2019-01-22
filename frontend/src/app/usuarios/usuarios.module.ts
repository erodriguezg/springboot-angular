import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditarComponent } from './editar/editar.component';
import { IndexComponent } from './index/index.component';
import { SharedTemplateModule } from 'app/shared-template/shared-template.module';

@NgModule({
  declarations: [EditarComponent, IndexComponent],
  imports: [
    CommonModule, SharedTemplateModule
  ]
})
export class UsuariosModule { }
