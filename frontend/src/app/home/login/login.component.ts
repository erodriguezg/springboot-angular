import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../../core/services/auth.service';

import { ConstantesUtils } from '../../core/utils/constantes.utils';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  msgs = [];

  ngOnInit() { }

  constructor(private authService: AuthService, private router: Router) {
      this.authService.logoutNoNavigate();
  }

  login() {
      this.authService.login(this.username, this.password).subscribe(res => {
          if (res.exitoLogin) {
              this.router.navigate([ConstantesUtils.ROUTE_EXITO_LOGIN]);
          } else {
              res.errores.forEach(error => {
                  this.msgs.push({severity: 'error', summary: 'Error', detail: error});
              });
          }
      });
  }

}
