import {Component} from '@angular/core';
import {AuthService} from '../service/auth.service';
import {Router} from '@angular/router';
import {Constantes} from '../constantes';

@Component({
    templateUrl: 'login.view.html'
})
// tslint:disable-next-line:component-class-suffix
export class LoginView {
    username: string;
    password: string;
    msgs = [];

    constructor(private authService: AuthService, private router: Router) {
        this.authService.logoutNoNavigate();
    }

    login() {
        this.authService.login(this.username, this.password).subscribe(res => {
            if (res.exitoLogin) {
                this.router.navigate([Constantes.ROUTE_EXITO_LOGIN]);
            } else {
                res.errores.forEach(error => {
                    this.msgs.push({severity: 'error', summary: 'Error', detail: error});
                });
            }
        });
    }
}
