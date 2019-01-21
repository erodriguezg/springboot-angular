import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RutTextPipe } from './pipes/ruttext.pipe';
import { BlockuiComponent } from './blockui/blockui.component';
import { ShellComponent } from './shell/shell.component';

@NgModule({
  declarations: [RutTextPipe, BlockuiComponent, ShellComponent],
  imports: [
    CommonModule
  ],
  exports: [RutTextPipe, BlockuiComponent]
})
export class CoreModule { }
