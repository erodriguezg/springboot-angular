import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {AppRoutes} from './app.routes';
import 'rxjs/add/operator/toPromise';

import {
    AccordionModule,
    AutoCompleteModule,
    BreadcrumbModule,
    ButtonModule,
    CalendarModule,
    CarouselModule,
    ChartModule,
    CheckboxModule,
    ChipsModule,
    CodeHighlighterModule,
    ColorPickerModule,
    ConfirmationService,
    ConfirmDialogModule,
    ContextMenuModule,
    DataGridModule,
    DataListModule,
    DataScrollerModule,
    DataTableModule,
    DialogModule,
    DragDropModule,
    DropdownModule,
    EditorModule,
    FieldsetModule,
    FileUploadModule,
    GalleriaModule,
    GMapModule,
    GrowlModule,
    InputMaskModule,
    InputSwitchModule,
    InputTextareaModule,
    InputTextModule,
    LightboxModule,
    ListboxModule,
    MegaMenuModule,
    MenubarModule,
    MenuModule,
    MessagesModule,
    MultiSelectModule,
    OrderListModule,
    OrganizationChartModule,
    OverlayPanelModule,
    PaginatorModule,
    PanelMenuModule,
    PanelModule,
    PasswordModule,
    PickListModule,
    ProgressBarModule,
    RadioButtonModule,
    RatingModule,
    ScheduleModule,
    SelectButtonModule,
    SharedModule,
    SlideMenuModule,
    SliderModule,
    SpinnerModule,
    SplitButtonModule,
    StepsModule,
    TabMenuModule,
    TabViewModule,
    TerminalModule,
    TieredMenuModule,
    ToggleButtonModule,
    ToolbarModule,
    TooltipModule,
    TreeModule,
    TreeTableModule
} from 'primeng/primeng';
import {TableModule} from 'primeng/table';
import {ScrollPanelModule} from 'primeng/scrollpanel';

import {NgIdleModule} from '@ng-idle/core';

import {AppComponent} from './app.component';
import {IdleDetectorComponent} from './app.idle-detector.component';
import {ScrollTopComponent} from './app.scroll-top.component';
import {AppTemplateComponent} from './app.template.component';

import {InicioView} from './view/inicio.view';

import { NgxWebstorageModule } from 'ngx-webstorage';
import {LoadingService} from './service/loading.service';
import {AuthService} from './service/auth.service';

import {AuthentificatedGuard} from './guard/authentificated.guard';
import {PermitAllGuard} from './guard/permit-all.guard';
import {AppLogoutComponent} from './app.logout.component';
import {ComunaService} from './service/comuna.service';
import {RegionService} from './service/region.service';
import {AppBlockuiComponent} from './app.blockui.component';
import {AppFooterComponent} from './app.footer.component';
import {AppMenuComponent, AppSubMenuComponent} from './app.menu.component';
import {AppTopBarComponent} from './app.topbar.component';
import {BlockuiService} from './service/blockui.service';
import {FlashScopeService} from './service/flash-scope.service';
import {RutTextPipe} from './pipes/ruttext.pipe';
import {UsuariosService} from './service/usuarios.service';
import {EditarUsuarioView} from './view/editar-usuario.view';
import {GestionarUsuariosView} from './view/gestionar-usuarios.view';
import {LoginView} from './view/login.view';
import {PerfilesService} from './service/perfiles.service';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {ApiUrlInterceptor} from './http/api-url.interceptor';
import {AuthInterceptor} from './http/auth.interceptor';
import {HttpService} from './service/http.service';
import {ErrorInterceptor} from './http/error.interceptor';
import {ShowcaseService} from './service/showcase.service';
import {ShowcaseView} from './view/showcase.view';
import {GlobalMessageService} from './service/global-message.service';
import {AppGlobalMessageComponent} from './app.global-message.component';
import {BlockuiInterceptor} from './http/blockui.interceptor';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}

@NgModule({
    imports: [
        BrowserModule,
        NgxWebstorageModule.forRoot(),
        FormsModule,
        ReactiveFormsModule,
        AppRoutes,
        HttpClientModule,
        BrowserAnimationsModule,
        AccordionModule,
        AutoCompleteModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CarouselModule,
        ColorPickerModule,
        ChartModule,
        CheckboxModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        SharedModule,
        ContextMenuModule,
        DataGridModule,
        DataListModule,
        DataScrollerModule,
        DataTableModule,
        DialogModule,
        DragDropModule,
        DropdownModule,
        EditorModule,
        FieldsetModule,
        FileUploadModule,
        GalleriaModule,
        GMapModule,
        GrowlModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OverlayPanelModule,
        OrganizationChartModule,
        PaginatorModule,
        PanelModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        RatingModule,
        ScheduleModule,
        SelectButtonModule,
        ScrollPanelModule,
        SlideMenuModule,
        SliderModule,
        SpinnerModule,
        SplitButtonModule,
        StepsModule,
        TabMenuModule,
        TableModule,
        TabViewModule,
        TerminalModule,
        TieredMenuModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        HttpClientModule,
        NgIdleModule.forRoot(),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        })
    ],
    declarations: [
        // Componentes
        AppComponent,
        AppTemplateComponent,
        AppMenuComponent,
        AppSubMenuComponent,
        AppTopBarComponent,
        AppFooterComponent,
        AppLogoutComponent,
        AppBlockuiComponent,
        AppGlobalMessageComponent,
        IdleDetectorComponent,
        ScrollTopComponent,
        // Vistas
        InicioView,
        LoginView,
        GestionarUsuariosView,
        EditarUsuarioView,
        ShowcaseView,
        // Pipes
        RutTextPipe
    ],
    providers: [
        // HTTP INTERCEPTORS
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ApiUrlInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: BlockuiInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true
        },
        // Others
        {
            provide: LocationStrategy,
            useClass: HashLocationStrategy
        },
        // Guards
        AuthentificatedGuard,
        PermitAllGuard,
        // Servicios
        HttpService,
        ConfirmationService,
        GlobalMessageService,
        BlockuiService,
        LoadingService,
        AuthService,
        ComunaService,
        RegionService,
        FlashScopeService,
        UsuariosService,
        PerfilesService,
        ShowcaseService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
