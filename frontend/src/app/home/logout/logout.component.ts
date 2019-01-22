import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { ConstantesUtils } from '../../core/utils/constantes.utils';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(public authService: AuthService, public router: Router) {
  }

  ngOnInit(): void {
      this.authService.logoutNoNavigate();
      this.router.navigate([ConstantesUtils.ROUTE_EXITO_LOGOUT]);
  }

}
