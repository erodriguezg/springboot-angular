import {Component} from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']
})
export class TemplateComponent {
  sidebarActive: boolean;
  onMenuButtonClick(event: Event) {
      this.sidebarActive = !this.sidebarActive;

      event.preventDefault();
  }
}
