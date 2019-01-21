import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditarComponent } from './editar/editar.component';
import { IndexComponent } from './index/index.component';

@NgModule({
  declarations: [EditarComponent, IndexComponent],
  imports: [
    CommonModule
  ]
})
export class UsuariosModule { }
