import {FormControl, FormGroup, ValidatorFn} from '@angular/forms';

export class CustomsValidators {

    static validateRUN(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (rutValidator.validar(formControl.value)) {
            return null;
        } else {
            return {
                validateRUN: {
                    valid: false
                }
            };
        }
    }

    static validateRun(run: string) {
        if (run != null) {
            return rutValidator.validar(run);
        } else {
            return false;
        }
    }

    static validateNumber(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        const isNum = /^\d+$/.test(formControl.value);
        if (isNum) {
            return null;
        } else {
            return {
                validateNumber: {
                    valid: false
                }
            };
        }
    }

    static validateEmail(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (emailValido(formControl.value)) {
            return null;
        } else {
            return {
                validateEmail: {
                    valid: false
                }
            };
        }
    }

    static matchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
        return (group: FormGroup) => {
            const passwordInput = group.controls[passwordKey];
            const passwordConfirmationInput = group.controls[passwordConfirmationKey];
            if (passwordInput.value !== passwordConfirmationInput.value) {
                return passwordConfirmationInput.setErrors({notEqualsPasswords: {valid: false}});
            }
        };
    }

    static validateUsername(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (usernameValido(formControl.value)) {
            return null;
        } else {
            return {
                validateUsername: {
                    valid: false
                }
            };
        }
    }
}

function emailValido(email: string) {
    const re = new RegExp(['^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)',
        '|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])',
        '|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$'].join(''), 'g');
    return re.test(email);
}

function usernameValido(username: string) {
    const re = /^[a-zA-Z0-9]+$/;
    return re.test(username);
}

const rutValidator = {

    formatear: function (Rut, digitoVerificador) {
        let sRut = Rut;
        let sRutFormateado = '';
        let sDV;
        sRut = this.quitarFormato(sRut);
        if (digitoVerificador) {
            sDV = sRut.charAt(sRut.length - 1);
            sRut = sRut.substring(0, sRut.length - 1);
        }
        while (sRut.length > 3) {
            sRutFormateado = '.' + sRut.substr(sRut.length - 3) + sRutFormateado;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormateado = sRut + sRutFormateado;
        if (sRutFormateado !== '' && digitoVerificador) {
            sRutFormateado += '-' + sDV;
        } else if (digitoVerificador) {
            sRutFormateado += sDV;
        }
        return sRutFormateado;
    },

    quitarFormato: function (rut) {
        let strRut = rut;
        while (strRut.indexOf('.') !== -1) {
            strRut = strRut.replace('.', '');
        }
        while (strRut.indexOf('-') !== -1) {
            strRut = strRut.replace('-', '');
        }
        return strRut;
    },

    digitoValido: function (dv): boolean {
        if (dv !== '0' && dv !== '1' && dv !== '2' && dv !== '3' && dv !== '4'
            && dv !== '5' && dv !== '6' && dv !== '7' && dv !== '8' && dv !== '9'
            && dv !== 'k' && dv !== 'K') {
            return false;
        }
        return true;
    },

    digitoCorrecto: function (crut): any {
        let rut;
        let dv;
        const largo = crut.length;
        let dvr;
        if (largo < 2) {
            return false;
        }
        if (largo > 2) {
            rut = crut.substring(0, largo - 1);
        } else {
            rut = crut.charAt(0);
        }
        dv = crut.charAt(largo - 1);
        this.digitoValido(dv);
        if (rut == null || dv == null) {
            return 0;
        }

        dvr = this.getDigito(rut);

        const normDv = (dv.toLowerCase() === 'k' || dv === 0 || dv === '0') ? 'k' : dv;
        console.log(dvr, normDv);
        return dvr.toString() === normDv || (dvr.toString() === '0' && normDv === 'k');
    },

    getDigito: function (rut): any {
        const dvr = '0';
        let suma = 0;
        let mul = 2;
        for (let i = rut.length - 1; i >= 0; i--) {
            suma = suma + rut.charAt(i) * mul;
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
    },

    validar: function (texto: string): boolean {
        if (texto.indexOf('-') !== texto.length - 2) {
            return false;
        }
        texto = this.quitarFormato(texto);
        const largo = texto.length;

        // rut muy corto
        if (largo < 2) {
            return false;
        }
        // verifica que los numeros correspondan a los de rut
        for (let i = 0; i < largo; i++) {
            // numero o letra que no corresponda a los del rut
            if (!this.digitoValido(texto.charAt(i))) {
                return false;
            }
        }

        let invertido = '';
        for (let i = (largo - 1), j = 0; i >= 0; i-- , j++) {
            invertido = invertido + texto.charAt(i);
        }
        let dtexto = '';
        dtexto = dtexto + invertido.charAt(0);
        dtexto = dtexto + '-';
        let cnt = 0;

        for (let i = 1, j = 2; i < largo; i++ , j++) {
            if (cnt === 3) {
                dtexto = dtexto + '.';
                j++;
                dtexto = dtexto + invertido.charAt(i);
                cnt = 1;
            } else {
                dtexto = dtexto + invertido.charAt(i);
                cnt++;
            }
        }

        invertido = '';
        for (let i = (dtexto.length - 1), j = 0; i >= 0; i-- , j++) {
            invertido = invertido + dtexto.charAt(i);
        }

        console.log(this.digitoCorrecto(texto), texto);
        if (this.digitoCorrecto(texto)) {
            return true;
        }
        return false;
    }
}
