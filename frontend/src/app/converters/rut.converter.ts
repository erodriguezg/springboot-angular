export class RutConverter {

    public static toNumber(rutTexto: string): number {
        let strRut: string = rutTexto.split('-')[0];
        while (strRut.indexOf('.') !== -1) {
            strRut = strRut.replace('.', '');
        }
        return parseInt(strRut, 10);
    }

    public static toString(rutInt: number): string {
        if (!rutInt) {
            return null;
        }
        const dv = RutConverter.generarDigitoVerificador(rutInt);
        let rutSalida = '';
        let rutAux = rutInt + '';
        while (rutAux.length > 3) {
            rutSalida = '.' + rutAux.substr(rutAux.length - 3) + rutSalida;
            rutAux = rutAux.substring(0, rutAux.length - 3);
        }
        rutSalida = rutAux +  rutSalida;
        return rutSalida + '-' + dv;
    }

    private static generarDigitoVerificador(rutInt: number) {
        const rut: string = rutInt + '';
        const dvr = '0';
        let suma = 0;
        let mul = 2;
        for (let i = rut.length - 1; i >= 0; i--) {
            suma = suma + parseInt(rut.charAt(i), 10) * mul;
            if (mul === 7) {
                mul = 2;
            } else {
                mul++;
            }
        }
        const res = suma % 11;
        if (res === 1) {
            return 'k';
        } else if (res === 0) {
            return '0';
        } else {
            return 11 - res;
        }
    }

}
