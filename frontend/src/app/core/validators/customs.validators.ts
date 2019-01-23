import {FormControl, FormGroup} from '@angular/forms';

export class CustomsValidators {

    static validateRUN(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (rutValidator.validate(formControl.value)) {
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
            return rutValidator.validate(run);
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

    static validatePercentage(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (formControl.value <= 100) {
            return null;
        } else {
            return {
                validatePercentage: {
                    valid: false
                }
            };
        }
    }

    static validateEmail(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (emailIsValid(formControl.value)) {
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
                return passwordConfirmationInput.setErrors({ notEqualsPasswords: { valid: false } });
            }
        };
    }

    static validateUsername(formControl: FormControl) {
        if (!formControl.value) {
            return null;
        }
        if (usernameIsValid(formControl.value)) {
            return null;
        } else {
            return {
                validateUsername: {
                    valid: false
                }
            };
        }
    }

    static notBlank(control: FormControl) {
        const empty = (control.value || '').trim().length === 0;
        return !empty ? null : {
            notBlank: {
                valid: false
            }
        };
    }
}

function emailIsValid(email: string) {
    const re = new RegExp(['^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)',
        '|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])',
        '|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$'].join(''), 'g');
    return re.test(email);
}

function usernameIsValid(username: string) {
    const re = /^[a-zA-Z0-9]+$/;
    return re.test(username);
}

const rutValidator = {

    format(rut, dv) {
        let sRut = rut;
        let sRutFormated = '';
        let sDV;
        sRut = this.removeFormat(sRut);
        if (dv) {
            sDV = sRut.charAt(sRut.length - 1);
            sRut = sRut.substring(0, sRut.length - 1);
        }
        while (sRut.length > 3) {
            sRutFormated = `.${sRut.substr(sRut.length - 3)}${sRutFormated}`;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormated = sRut + sRutFormated;
        if (sRutFormated !== '' && dv) {
            sRutFormated += '-' + sDV;
        } else if (dv) {
            sRutFormated += sDV;
        }
        return sRutFormated;
    },

    removeFormat(rut) {
        let strRut = rut;
        while (strRut.indexOf('.') !== -1) {
            strRut = strRut.replace('.', '');
        }
        while (strRut.indexOf('-') !== -1) {
            strRut = strRut.replace('-', '');
        }
        return strRut;
    },

    dvIsValid(dv): boolean {
        return !(dv !== '0' && dv !== '1' && dv !== '2' && dv !== '3' && dv !== '4'
            && dv !== '5' && dv !== '6' && dv !== '7' && dv !== '8' && dv !== '9'
            && dv !== 'k' && dv !== 'K');

    },

    validateDv(crut): any {
        let rut;
        let dv;
        const rutLength = crut.length;
        let dvr;
        if (rutLength < 2) {
            return false;
        }
        if (rutLength > 2) {
            rut = crut.substring(0, rutLength - 1);
        } else {
            rut = crut.charAt(0);
        }
        dv = crut.charAt(rutLength - 1);
        this.dvIsValid(dv);
        if (rut == null || dv == null) {
            return 0;
        }

        dvr = this.getDv(rut);

        const normDv = (dv.toLowerCase() === 'k' || dv === 0 || dv === '0') ? 'k' : dv;
        return dvr.toString() === normDv || (dvr.toString() === '0' && normDv === 'k');
    },

    getDv(rut): any {
        let sum = 0;
        let mul = 2;
        for (let i = rut.length - 1; i >= 0; i--) {
            sum = sum + rut.charAt(i) * mul;
            if (mul === 7) {
                mul = 2;
            } else {
                mul++;
            }
        }
        const res = sum % 11;
        if (res === 1) {
            return 'k';
        } else if (res === 0) {
            return '0';
        } else {
            return 11 - res;
        }
    },

    validate(text: string): boolean {
        if (text.indexOf('-') !== text.length - 2) {
            return false;
        }
        text = this.removeFormat(text);
        const length = text.length;

        // rut too short
        if (length < 2) {
            return false;
        }
        // verifica que los numeros correspondan a los de rut
        for (let i = 0; i < length; i++) {
            // numero o letra que no corresponda a los del rut
            if (!this.dvIsValid(text.charAt(i))) {
                return false;
            }
        }

        let reversedRut = '';
        for (let i = (length - 1), j = 0; i >= 0; i-- , j++) {
            reversedRut = reversedRut + text.charAt(i);
        }
        let dtext = '';
        dtext = dtext + reversedRut.charAt(0);
        dtext = dtext + '-';
        let cnt = 0;

        for (let i = 1, j = 2; i < length; i++ , j++) {
            if (cnt === 3) {
                dtext = dtext + '.';
                j++;
                dtext = dtext + reversedRut.charAt(i);
                cnt = 1;
            } else {
                dtext = dtext + reversedRut.charAt(i);
                cnt++;
            }
        }

        reversedRut = '';
        for (let i = (dtext.length - 1), j = 0; i >= 0; i-- , j++) {
            reversedRut = reversedRut + dtext.charAt(i);
        }

        return !!this.validateDv(text);
    }
};
