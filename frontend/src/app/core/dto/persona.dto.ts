import { ComunaDto } from './comuna.dto';

export class PersonaDto {
    idPersona: number;
    run: number;
    nombres: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    email: string;
    fechaNacimiento: Date;
    telefono: string;
    comuna: ComunaDto;

    apellidos(): string {
        return `${this.apellidoPaterno} ${this.apellidoMaterno}`;
    }
}
