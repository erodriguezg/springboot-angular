import {Component} from '@angular/core';

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
