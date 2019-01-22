import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TemplateComponent } from './template/template.component';
import { MenuComponent, SubMenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { TopbarComponent } from './topbar/topbar.component';

/* primeng */
import { ScrollPanelModule } from 'primeng/primeng';


@NgModule({
  declarations: [TemplateComponent, MenuComponent, FooterComponent, TopbarComponent, SubMenuComponent],
  imports: [
    CommonModule,
    RouterModule,
    /*primeng*/
    ScrollPanelModule
  ],
  exports: [TemplateComponent]
})
export class SharedTemplateModule { }
