import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RutTextPipe } from './pipes/ruttext.pipe';

@NgModule({
  declarations: [
    RutTextPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    RutTextPipe
  ]
})
export class SharedCommonsModule { }
