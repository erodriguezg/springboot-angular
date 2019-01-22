import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';

import { RutTextPipe } from './pipes/ruttext.pipe';
import { BlockuiComponent } from './blockui/blockui.component';
import { ShellComponent } from './shell/shell.component';
import { FooterComponent } from './footer/footer.component';
import { GlobalMessageComponent } from './global-message/global-message.component';
import { IdleDetectorComponent } from './idle-detector/idle-detector.component';
import { MenuComponent, SubMenuComponent } from './menu/menu.component';
import { TemplateComponent } from './template/template.component';
import { ScrollTopComponent } from './scroll-top/scroll-top.component';
import { TopbarComponent } from './topbar/topbar.component';

/* primeng */
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { ScrollPanelModule } from 'primeng/scrollpanel';

/* Translate */
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NgIdleModule } from '@ng-idle/core';
import { NgxWebstorageModule } from 'ngx-webstorage';

/* AoT requires an exported function for factories */
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    RutTextPipe,
    BlockuiComponent,
    ShellComponent,
    FooterComponent,
    GlobalMessageComponent,
    IdleDetectorComponent,
    MenuComponent,
    SubMenuComponent,
    TemplateComponent,
    ScrollTopComponent,
    TopbarComponent
  ],
  imports: [
    /* primeng */
    DialogModule,
    ButtonModule,
    ScrollPanelModule,
    /* otros */
    CommonModule,
    HttpClientModule,
    NgIdleModule.forRoot(),
    NgxWebstorageModule.forRoot(),
    RouterModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  })
  ],
  providers: [ HttpClient],
  exports: [
    RutTextPipe,
    ShellComponent]
})
export class CoreModule { }
