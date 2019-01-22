import { SelectItem } from 'primeng/api';

export class ConstantesUtils {

    /* messages */
    static readonly MSJ_SIN_RESULTADOS_FILTROS = 'No se encontraron resultados';

    /* id perfiles */
    static readonly ID_PERFIL_ADMINISTRADOR: number = 1;
    static readonly ID_PERFIL_USUARIO: number = 2;

    /* parametros flash scope */
    static readonly FS_KEY_ID_USUARIO: string = 'ID_USUARIO_KEY';

    private constructor() {
    }

    static createSeleccionarSelectItem() {
        return <SelectItem>{label: 'Seleccionar', value: null};
    }

    static createTodosSelectItem() {
        return <SelectItem>{label: 'Todos', value: null};
    }

    static createHabilitadosSelectItems(): SelectItem[] {
        return [
            ConstantesUtils.createTodosSelectItem(),
            {label: 'habilitado', value: true},
            {label: 'deshabilitado', value: false}
        ];
    }

}
