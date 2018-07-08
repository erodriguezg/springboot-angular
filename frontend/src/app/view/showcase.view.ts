import {Component, OnInit} from '@angular/core';
import {ShowcaseService} from '../service/showcase.service';

@Component({
    templateUrl: 'showcase.view.html'
})
// tslint:disable-next-line:component-class-suffix
export class ShowcaseView implements OnInit {

    constructor(private showcaseService: ShowcaseService) {}

    ngOnInit(): void {
    }

    public lanzarErrorNegocioAction(): void {
        this.showcaseService.lanzarErrorNegocio()
            .subscribe(() => console.log('aquí no deberia llegar!'));
    }

    public lanzarErrorInternoAction(): void {
        this.showcaseService.lanzarErrorInterno()
            .subscribe( () => console.log('aquí tampoco deberia llegar!'));
    }

    public lanzarErrorEntradaAction(): void {
        // queda pendiente
    }

    public accederAccionAdministradorAction(): void {
        this.showcaseService.accederAccionAdministrador()
            .subscribe( () => console.log('si llegue aquí es pq soy admin!'));
    }

    public procesarEnBackendAction(): void {
        console.log('mandando a dormir');
        this.showcaseService.procesarEnBackend()
            .subscribe(() => console.log('ya desperte!'));
    }

}
