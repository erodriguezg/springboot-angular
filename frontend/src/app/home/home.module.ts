import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [IndexComponent, LoginComponent],
  imports: [
    CommonModule
  ]
})
export class HomeModule { }
