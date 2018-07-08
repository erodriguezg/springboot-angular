import {Subject} from 'rxjs/Subject';

export class BeginLoadingEventEmmiter extends Subject<String> {

    constructor() {
        super();
    }

    emit(value) {
        super.next(value);
    }
}

export class EndLoadingEventEmmiter extends Subject<String> {

    constructor() {
        super();
    }

    emit(value) {
        super.next(value);
    }
}
