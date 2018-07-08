import {Component} from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
    templateUrl: './app.template.component.html'
})
export class AppTemplateComponent {

  sidebarActive: boolean;

    onMenuButtonClick(event: Event) {
        this.sidebarActive = !this.sidebarActive;

        event.preventDefault();
    }

}
