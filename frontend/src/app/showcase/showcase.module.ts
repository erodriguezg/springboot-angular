import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { ShowcaseRoutingModule } from './showcase-routing.module';
import { SharedTemplateModule } from '../shared-template/shared-template.module';

/* primeng */
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { FormsModule } from '@angular/forms';
import { GrowlModule } from 'primeng/components/growl/growl';


@NgModule({
  declarations: [IndexComponent],
  imports: [
    CommonModule,
    ShowcaseRoutingModule,
    SharedTemplateModule,
    /*primeng*/
    ButtonModule
  ]
})
export class ShowcaseModule { }
