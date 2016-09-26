import {Component,ViewChild} from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { MockCfg } from '../../mock';
import { AlarmInfo,ModifyTypeInfo } from '../../shared/index';
import {Ng2Bs3ModalModule,ModalComponent} from 'ng2-bs3-modal/ng2-bs3-modal';
import { Router} from '@angular/router';
import { CookieService} from 'angular2-cookie/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

var _ = require('underscore');
@Component({
  moduleId: module.id,
  selector:'all-alarms',
  templateUrl:'../alarmInfo.html',
  styleUrls:['../alarmInfo.css'],

  providers: [CookieService]
})
export class AllAlarmsComponent {
  @ViewChild('modal')
  modal:ModalComponent;
  alarmType:string;
  curType:string;
  loginLink = '/login';
  private freshTimersInfo = new Map<string,any>();
  phoneNumber:string;
  alarmID:string;
  userName:string;
  loginStatus:boolean;
  modifyType:string;
  modifyContent:string;
  type:string;
  recordingContent:string;
  private requestOptions:any;
  private alarmsInfo:Array<AlarmInfo>;
  private currentClickAlarmInfo:AlarmInfo;
  private hasAlarms:boolean = false;
  private clickButtonType:string;
  private recordingInfo:string;
  private currentRecordingInfo:string;
  private recordingType:string;
  currentSelectValue:string;
  UsersList:Array<any>;
  formUserName:FormControl;
  reciveMember:string;

  constructor(private _http: Http,private _router: Router,private _cookieService:CookieService) {
    this.alarmType = "所有告警";
    this.curType = "";
    this.phoneNumber = "";
    this.alarmID = "";
    this.userName = "";
    this.modifyContent = "";
    this.recordingContent = "";
    this.recordingType = "";
    this.modifyType = "";
    this.loginStatus = false;
    this.type = "";
    this.clickButtonType = "";
    this.currentSelectValue = "";
    this.recordingInfo = '';
    this.currentRecordingInfo = "";
    this.alarmsInfo = [];
    this.UsersList = [];
    this.reciveMember = "";


    this.requestAlarmsData();
    this.formUserName = new FormControl('');
    let cookies:any = this._cookieService.getObject("springboot.grs.info");
    if(null === cookies || undefined === cookies) {
      this._router.navigateByUrl(this.loginLink);
      return ;
    }
    this.userName = cookies.name;
    this.phoneNumber = cookies.phoneNumber;

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this._http.request(MockCfg.baseUrl + MockCfg.getUsersUrl,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .subscribe((res:Response) => {
        let userslist:any = _.filter(res.json(),function(data:any){
          return data.phoneNumber.indexOf(cookies.phoneNumber) == -1;
        });
        this.UsersList = userslist;
        console.log(this.UsersList);
      });

  }
  readSelect(event:any){
    console.log(event.target.value);
  }
  clearCurrentAlarmRecordingInfo(){
    this.currentRecordingInfo = '';
  }
  // 备注记录告警信息
  currentAlarmRecordingInfo(){
    console.log(this.currentRecordingInfo);
    // let str:string = "备注告警信息 ";
    // let _currentRecordingInfo:string =
    //   '{' +
    //   '"alarmId":"'+this.currentClickAlarmInfo.alarmID+'",'+
    //   '"recordingMember":"'+this.userName+'",'+
    //   '"recordingType":"'+str+'",'+
    //   '"recordingContext":"'+this.currentRecordingInfo+'"'+
    //   '}';
    // console.log(_currentRecordingInfo);
    //   let headers = new Headers();
    //   headers.append('Content-Type', 'application/json');
    //   this._http.post(MockCfg.baseUrl + MockCfg.recordLogUrl,_currentRecordingInfo,<RequestOptionsArgs> {headers: headers, withCredentials: true})
    //     .subscribe((res:Response) => {
    //
    //     console.log(res.json());
    //   });
  }
  curentAlarmOnKey(event:any){
    this.currentRecordingInfo = event.target.value;
  }
  // without strong typing
  onKey(event:any) {
    this.recordingInfo = event.target.value;
  }
  onReciveMemberKey(event:any){
    this.reciveMember = event.target.value;
  }
  clickalarmList(cClickAlarmInfo:AlarmInfo){
    this.currentClickAlarmInfo = cClickAlarmInfo;
    console.log("clickalarmList");
    console.log(this.currentClickAlarmInfo);
  }
  setCurrentUser(name:string){
    console.log("name");
    console.log(12122121212);
  }
  closed(){

  }
  confirm(clickButtonType:string){

    this.alarmID = this.currentClickAlarmInfo.alarmID.toString();
    console.log(this.alarmID);
    this.modifyType = "alarmStatus";
    this.recordingContent = this.recordingInfo;
    let name:string = this.userName;
    if(clickButtonType === "处理告警"){
      this.modifyContent = "1";
      this.recordingType = "认领告警";
    } else if(clickButtonType === "转发告警"){
      this.modifyContent = "2";

      this.recordingType = "转发告警给 " + this.reciveMember;
      name = this.reciveMember;
    } else if(clickButtonType === "关闭告警"){
      this.modifyContent = "3";
      this.recordingType = "关闭告警";
    }
    let modifyTypeInfo:string =
      '{' +
      '"alarmID":'+this.alarmID+','+
      '"loginStatus":'+this.loginStatus+','+
      '"userID":"'+this.phoneNumber+'",'+
      '"userName":"'+name+'",'+
      '"modifyType":"'+this.modifyType+'",'+
      '"modifyContent":"'+this.modifyContent+'",'+
      '"recordingType":"'+this.recordingType+'",'+
      '"recordingContent":"'+this.recordingContent+'"'+
      '}';

    console.log(modifyTypeInfo);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this._http.post(MockCfg.baseUrl + MockCfg.modifyTypeInfoUrl,modifyTypeInfo,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .subscribe((res:Response) => {

        console.log(res.json());
        this.requestAlarmsData();
      });
  }

  clearRecordingInfo(){
    this.recordingInfo = '';
  }
  dealAlarm(type:string){
    console.log(type);
    this.clickButtonType = type;

  }
  ngOnInit() {
    console.log("ngOnInit");
    let freshTimer = setInterval(() => {
      console.log("my-alarms 定时器正在运行....");
      console.log(this.currentSelectValue);
      this.requestAlarmsData();
    }, 5000);
    this.freshTimersInfo.set('my-alarms', freshTimer);
    console.log("my-alarms 定时器启动");
  }
  private requestAlarmsData() {
    console.log(this.recordingInfo);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this._http.request(MockCfg.baseUrl + MockCfg.alarmsInfoUrl,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .subscribe((res:Response) => {
        this.alarmsInfo = res.json().alarmsInfo;
        if(this.alarmsInfo.length > 0){
          this.hasAlarms = true;
          // 验证队列里面是否有当前点击的告警信息;
          let hasFlag:boolean = false;
          for(let i:number = 0; i<this.alarmsInfo.length;i++){
            if(this.currentClickAlarmInfo) {
              if(this.currentClickAlarmInfo.alarmID === this.alarmsInfo[i].alarmID){
                this.currentClickAlarmInfo = this.alarmsInfo[i];
                hasFlag = true;
                break;
              }
            }
          }
          if(!hasFlag && this.alarmsInfo[0]) {
            // 新查询到的告警列表已无当前告警，则点击告警列表第一个
            this.clickalarmList(this.alarmsInfo[0]);
          }
        }else {
          this.hasAlarms = false
        }
        console.log(this.alarmsInfo);
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
    this.closeFresh("my-alarms");
  }
}




