import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TemplateComponent } from './template/template.component';
import { MenuComponent, SubMenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { TopbarComponent } from './topbar/topbar.component';

/* primeng */

import {ButtonModule} from 'primeng/button';
import {ScrollPanelModule} from 'primeng/scrollpanel';


@NgModule({
  declarations: [TemplateComponent, MenuComponent, FooterComponent, TopbarComponent, SubMenuComponent],
  imports: [
    CommonModule,
    RouterModule,
    /*primeng*/
    ButtonModule,
    ScrollPanelModule
  ],
  exports: [TemplateComponent]
})
export class SharedTemplateModule { }
