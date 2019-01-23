import { Component, OnInit } from '@angular/core';
import { ENVIRONMENT } from '../../../environments/environment.prod';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  version: string;

  constructor() { }

  ngOnInit() {
    this.version = ENVIRONMENT.VERSION;
  }

}
