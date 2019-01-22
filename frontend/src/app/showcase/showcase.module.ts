import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { ShowcaseRoutingModule } from './showcase-routing.module';
import { SharedTemplateModule } from '../shared-template/shared-template.module';

@NgModule({
  declarations: [IndexComponent],
  imports: [CommonModule, ShowcaseRoutingModule, SharedTemplateModule]
})
export class ShowcaseModule { }
