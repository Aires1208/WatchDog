import {Directive, ElementRef,Component,Input} from '@angular/core';
import {Router} from '@angular/router';
@Component({
  moduleId: module.id,
  selector:'header-register',
  templateUrl:'header-register.component.html',
  styleUrls:['header-register.component.css']
})

export class HeaderRegisterComponent {
  loginLink = '/login';
  constructor(private _router: Router) {
  }

  login() {
    this._router.navigateByUrl(this.loginLink);
  }
}

