import {SelectItem} from 'primeng/api';

export class PerfilDto {
    idPerfilUsuario: number;
    nombre: string;

    public static perfilToSelectItem(perfil: PerfilDto): SelectItem {
        return <SelectItem>{label: perfil.nombre, value: perfil};
    }
}
