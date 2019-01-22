import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeRoutingModule } from './home-routing.module';
import { SharedTemplateModule } from '../shared-template/shared-template.module';

/* primeng */
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { GrowlModule } from 'primeng/components/growl/growl';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';

@NgModule({
  declarations: [
    IndexComponent,
    LoginComponent,
    LogoutComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HomeRoutingModule,
    SharedTemplateModule,
    /* primeng */
    ButtonModule,
    InputTextModule,
    ToastModule,
    GrowlModule
  ]
})
export class HomeModule { }
