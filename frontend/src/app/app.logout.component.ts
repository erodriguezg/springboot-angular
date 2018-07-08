import {Component, OnInit} from '@angular/core';
import {AuthService} from './service/auth.service';
import {Router} from '@angular/router';
import {Constantes} from './constantes';

@Component({
    template: `<p> logout ... </p>`
})
export class AppLogoutComponent implements OnInit {

    constructor(public authService: AuthService, public router: Router) {
    }

    ngOnInit(): void {
        this.authService.logoutNoNavigate();
        this.router.navigate([Constantes.ROUTE_EXITO_LOGOUT]);
    }

}
