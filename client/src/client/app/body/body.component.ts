import {Component} from '@angular/core';
import { UserService } from '../shared/services/user/user.service';
import {NgSwitch, NgSwitchCase, NgSwitchDefault} from "@angular/common";
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { CookieService} from 'angular2-cookie/core';
import { Router} from '@angular/router';
import { MockCfg } from '../mock';
import { AlarmInfo } from '../shared/index';
var _ = require('underscore');
@Component({
  moduleId: module.id,
  selector: 'body-alarm',
  templateUrl:'body.component.html',
  styleUrls:['body.component.css'],
  providers: [CookieService]
})
export class BodyComponent {
  userName: string;
  phoneNumber:string;
  loginLink = '/login';
  private freshTimersInfo = new Map<string,any>();
  private alarmsInfo:Array<AlarmInfo>;

  myAlarms:number;
  myAlarmsActive:number;
  myAlarmsAck:number;
  myAlarmsForwarded:number;
  myAlarmsClosed:number;

  allAlarms:number;
  allAlarmsActive:number;
  allAlarmsAck:number;
  allAlarmsClosed:number;
  constructor(private _cookieService:CookieService,private _http: Http,
              private _userService: UserService,private _router: Router) {
    this.myAlarms = 0;
    this.myAlarmsActive = 0;
    this.myAlarmsAck = 0;
    this.myAlarmsForwarded = 0;
    this.myAlarmsClosed = 0;

    this.allAlarms = 0;
    this.allAlarmsActive = 0;
    this.allAlarmsAck = 0;
    this.allAlarmsClosed = 0;

    this.userName = "";
  }
  ngOnInit() {
    this.getUserCookiesAndIsAutoLogin();
    this.requestAlarmsData();
    console.log("ngOnInit");
    let freshTimer = setInterval(() => {
      console.log("body-alarms 定时器正在运行....");
      this.requestAlarmsData();
    }, 5000);
    this.freshTimersInfo.set('my-alarms', freshTimer);
    console.log("body-alarms 定时器启动");
  }
  getUserCookiesAndIsAutoLogin(){
    console.log("获取是否已登录的标志");
    var flag = this._cookieService.get("springboot.grs.loginStatus");
    // flag为true则可自动登录;
    if(undefined == flag || !flag) {
      console.log("状态为未登录，则跳转到登陆界面");
      this._router.navigateByUrl(this.loginLink);
      return ;
    } else {
      console.log("状态为已登录");
      let cookies:any = this._cookieService.getObject("springboot.grs.info");
      if(null === cookies || undefined === cookies) {
        this._router.navigateByUrl(this.loginLink);
        return ;
      }
      this.userName = cookies.name;
      this.phoneNumber = cookies.phoneNumber;

    }
  }
  // 登陆异常处理函数;
   handleError (error: Response) {
    // console.log("登陆请求失败!");
  }

  requestAlarmsData() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this._http.request(MockCfg.baseUrl + MockCfg.alarmsInfoUrl,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .subscribe((res:Response) => {
        var name = this.userName;
        this.alarmsInfo = res.json().alarmsInfo;
        // 当前无团队功能，无添加app,默认数据库的所有用户都是一个团队，都维护一个app;
        this.myAlarms = this.alarmsInfo.length;
        this.allAlarms = this.alarmsInfo.length;

        let _myAlarmsActive:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          return data.alarmStatue.indexOf("待处理") != -1;
        });
        this.myAlarmsActive = _myAlarmsActive.length;
        this.allAlarmsActive = _myAlarmsActive.length;

        let _myAlarmsAck:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          return data.alarmStatue.indexOf("处理中") != -1 || (data.alarmStatue.indexOf("已转交") != -1
            && data.processingMember === name);
        });
        this.myAlarmsAck = _myAlarmsAck.length;

        let _allAlarmsAck:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          return data.alarmStatue.indexOf("处理中") != -1 || data.alarmStatue.indexOf("已转交") != -1;
        });
        this.allAlarmsAck = _allAlarmsAck.length;

        // 应该是状态是处理中，当前处理人不是自己，但是历史处理人有自己;
        let _myAlarmsForwarded:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          let flag:boolean = false;
          if(data.alarmStatue.indexOf("已转交") != -1){
            for(let i=0;i<data.historyProcessingMember.length;i++){
              if(data.historyProcessingMember[i].processingMember === name){
                flag = true;
                break;
              }
            }
          }
          return data.alarmStatue.indexOf("已转交") != -1 && data.processingMember.indexOf(name) == -1 && flag;
        });

        this.myAlarmsForwarded = _myAlarmsForwarded.length;

        let _myAlarmsClosed:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          return data.alarmStatue.indexOf("已关闭") != -1;
        });
        this.myAlarmsClosed = _myAlarmsClosed.length;
        this.allAlarmsClosed = _myAlarmsClosed.length;

      });
  }
  private closeFresh(type:string) {
    if (this.freshTimersInfo.get(type)) {
      clearInterval(this.freshTimersInfo.get(type));
      console.log(type + " 定时器关闭");
    }
  }
  ngOnDestroy() {
    console.log("ngOnDestroy");
    this.closeFresh("body-alarms");
  }
}

