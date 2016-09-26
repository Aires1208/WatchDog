import {Component, OnInit, OnDestroy} from "@angular/core";
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router} from '@angular/router';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { UserService } from '../shared/services/user/user.service';
import { Observable } from 'rxjs/Observable';
import { CookieService} from 'angular2-cookie/core';

import { MockCfg } from '../mock';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/toPromise';
@Component({
  selector: 'grs-register',
  templateUrl: 'app/register/register.html',
  styleUrls: ['app/register/register.css'],
  providers: [CookieService]

})
export class RegisterComponent implements OnInit, OnDestroy {

  logining:boolean;
  userID: FormControl;
  userPwd: FormControl;
  userRePwd: FormControl;
  validateCode: FormControl;
  // true为已登录，false为未登录
  loginStatus: FormControl;
  // 自动登录状态，true为自动登录，false为不自动登录。
  autoLoginStatus: FormControl;
  isAuto: FormControl;
  form: FormGroup;
  imagecodeUrl: string;
  errorDiagnostic: string;
  validatereToken: string;
  isIndeterminate: boolean = false;
  isChecked: boolean = false;
  openEyes:boolean = false;
  passwordInputType:String = "password";
  constructor(private http: Http,private _router: Router,
              private _cookieService:CookieService,private _userService: UserService) {
    this.logining=false;
    this.errorDiagnostic = "";
    this.validatereToken = "";
    this.imagecodeUrl = MockCfg.baseUrl + MockCfg.getimagecodeUrl;

  }
  // 切换显示密码或掩藏密码
  openOrCloseEyes(){
    this.openEyes = !this.openEyes;
    if(this.openEyes){
      this.passwordInputType = "text";
    } else {
      this.passwordInputType = "password";
    }
  }

  // 生成定长随机字符串;
  /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
  randomString(len:number) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = $chars.length;
    var pwd = '';
    for (let i = 0; i < len; i++) {
      pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
  }

  ngOnInit() {
    this.userID = new FormControl('', Validators.compose([Validators.required,
      Validators.minLength(11), Validators.maxLength(11),Validators.pattern('^[0-9]*$')]));
    this.userPwd = new FormControl('', Validators.compose([Validators.required,
      Validators.minLength(6), Validators.maxLength(20),Validators.pattern('^[A-Za-z0-9]+$')]));
    this.userRePwd = new FormControl('', Validators.compose([Validators.required,
      Validators.minLength(6), Validators.maxLength(20),Validators.pattern('^[A-Za-z0-9]+$')]));

    this.validateCode = new FormControl('', Validators.compose([Validators.required,
      Validators.minLength(4), Validators.maxLength(4)]));
    this.loginStatus = new FormControl('');
    this.form = new FormGroup({
      'userID': this.userID,
      'userPwd': this.userPwd,
      'userRePwd': this.userRePwd,
      'validateCode': this.validateCode,
      'loginStatus':this.loginStatus
    });
    this.changeImg();
  }
  // 向服务端获取验证码图片，并传一个token给后端;
  changeImg(){
    let mytimeStr: String = new Date().getTime().toString();
    this.validatereToken = mytimeStr + this.randomString(5);
    this.imagecodeUrl = MockCfg.baseUrl + MockCfg.getimagecodeUrl+
      "?reToken="+this.validatereToken + "&time="+new Date();
  }
  // 提交登陆信息;
  onSubmit() {
    this.logining = true;

  }
  // 登录时用户信息写入cookie
  setUserCookies(resMsg:any,flag:boolean){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() + 7);
    this.form.value.alaiStr = resMsg.alaiStr;
    this._cookieService.putObject("springboot.grs.id",this.form.value, {'expires': expireDate.toUTCString()});
  }

  ngOnDestroy() {
  }
}
