import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import { MockCfg } from '../../../mock';

import { User } from './user';

@Injectable()
export class UserService {
  headerSidbarType:string = "alarm";
  showLoginRigster:boolean = true;
  currentUserName:string = "";

  constructor (private http: Http) {

  }
  private _loginApi = MockCfg.baseUrl + MockCfg.loginUrl;
  private _registerApi = MockCfg.baseUrl + MockCfg.registerUrl;

  private handleError (error: Response) {
    console.log("登陆请求失败!");
    return Observable.throw(error || "Server Error");
  }

  login(user:User) {
    let body = JSON.stringify(user);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(this._loginApi, body,<RequestOptionsArgs> {headers: headers, withCredentials: true})
                    .map((res: Response) => res)
                    .catch(this.handleError);
  }

  getUserNameByPhoneNumber(user:User) {
    let body = JSON.stringify(user);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(MockCfg.baseUrl+MockCfg.getsessionidUrl, body,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .map((res: Response) => res)
      .catch(this.handleError);
  }

  register(user:User) {
    let body = JSON.stringify(user);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(this._registerApi, body, <RequestOptionsArgs> {headers: headers, withCredentials: true})
                    .map((res: Response) => res)
                    .catch(this.handleError);
  }


}
