import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';

@Injectable()
export class PermitAllGuard implements CanActivate {

    canActivate() {
        return true;
    }
}
