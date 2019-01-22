import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RutTextPipe } from './pipes/ruttext.pipe';
import { BlockuiComponent } from './blockui/blockui.component';
import { ShellComponent } from './shell/shell.component';
import { FooterComponent } from './footer/footer.component';
import { GlobalMessageComponent } from './global-message/global-message.component';
import { IdleDetectorComponent } from './idle-detector/idle-detector.component';
import { MenuComponent } from './menu/menu.component';
import { TemplateComponent } from './template/template.component';
import { ScrollTopComponent } from './scroll-top/scroll-top.component';
import { TopbarComponent } from './topbar/topbar.component';

@NgModule({
  declarations: [RutTextPipe, BlockuiComponent, ShellComponent, FooterComponent, GlobalMessageComponent, IdleDetectorComponent, MenuComponent, TemplateComponent, ScrollTopComponent, TopbarComponent],
  imports: [
    CommonModule
  ],
  exports: [RutTextPipe, ShellComponent]
})
export class CoreModule { }
