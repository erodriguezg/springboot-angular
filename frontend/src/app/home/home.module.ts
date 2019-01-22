import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [IndexComponent, LoginComponent, LogoutComponent],
  imports: [
    CommonModule
  ]
})
export class HomeModule { }
