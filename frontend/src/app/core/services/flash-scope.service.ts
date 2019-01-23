import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class FlashScopeService {

    private flashMap: Map<string, any>;

    constructor() {
        this.flashMap = new Map();
    }

    public add(key: string, value: any) {
        this.flashMap.set(key, value);
    }

    public get(key: string): any {
        const value: any = this.flashMap.get(key);
        if (value) {
            this.flashMap.delete(key);
        }
        return value;
    }

}
