import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { ShowcaseRoutingModule } from './showcase-routing.module';

@NgModule({
  declarations: [IndexComponent],
  imports: [CommonModule, ShowcaseRoutingModule]
})
export class ShowcaseModule { }
