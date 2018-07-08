import {Pipe, PipeTransform} from '@angular/core';
import {RutConverter} from '../converters/rut.converter';

@Pipe({name: 'rutText'})
export class RutTextPipe implements PipeTransform {
    transform(rutEntero: number): any {
        return RutConverter.toString(rutEntero);
    }
}
