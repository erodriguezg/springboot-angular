import {Injectable} from '@angular/core';
import {CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../service/auth.service';

@Injectable()
export class AuthentificatedGuard implements CanActivate {

    constructor(private loginService: AuthService, private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if (this.loginService.isLogged()) {
            return true;
        } else {
            this.router.navigate(['/']);
            return false;
        }
    }
}

