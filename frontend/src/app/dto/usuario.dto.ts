import {PerfilDto} from './perfil.dto';
import {PersonaDto} from './persona.dto';

export class UsuarioDto {
    idPersona: number;
    username: string;
    password: string;
    persona: PersonaDto;
    habilitado: boolean;
    perfil: PerfilDto;
}
