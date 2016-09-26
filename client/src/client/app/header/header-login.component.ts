import {Directive, ElementRef,Component,Input} from '@angular/core';
import {Router} from '@angular/router';
@Component({
  moduleId: module.id,
  selector:'header-login',
  templateUrl:'header-login.component.html',
  styleUrls:['header-login.component.css']
})

export class HeaderLoginComponent {
  registerLink = '/register';
  constructor(private _router: Router) {
  }

  register() {
    this._router.navigateByUrl(this.registerLink);
  }
}

