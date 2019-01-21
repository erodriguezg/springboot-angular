import {Injectable} from '@angular/core';
import {BeginLoadingEventEmmiter, EndLoadingEventEmmiter} from '../domain/emitters';

@Injectable({
    providedIn: 'root'
  })
export class LoadingService {
   beginLoading: BeginLoadingEventEmmiter;
   endLoading: EndLoadingEventEmmiter;
   public blocked = false;

   constructor() {
       this.beginLoading = new BeginLoadingEventEmmiter();
       this.endLoading = new EndLoadingEventEmmiter();
   }

}
