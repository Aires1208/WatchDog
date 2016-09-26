import {Component,ViewChild} from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { MockCfg } from '../../mock';
import { AlarmInfo } from '../../shared/index';
import {Ng2Bs3ModalModule,ModalComponent} from 'ng2-bs3-modal/ng2-bs3-modal';
var _ = require('underscore');
@Component({
  moduleId: module.id,
  selector:'my-alarms',
  templateUrl:'../alarmInfo.html',
  styleUrls:['../alarmInfo.css']
})
export class MyAlarmsComponent {
  @ViewChild('modal')
  modal:ModalComponent;
  alarmType:string;
  curType:string;

  private freshTimersInfo = new Map<string,any>();
  userID:string;
  type:string;
  private requestOptions:any;
  private alarmsInfo:Array<AlarmInfo>;
  private currentClickAlarmInfo:AlarmInfo;
  private hasAlarms:boolean = false;
  private clickButtonType:string;
  private recordingInfo:string;
  private currentRecordingInfo:string
  constructor(private _http: Http) {
    this.alarmType = "我的告警";
    this.curType = "";
    this.userID = "";
    this.type = "";
    this.clickButtonType = "";
    this.recordingInfo = '';
    this.currentRecordingInfo = "";
    this.alarmsInfo = [];
    this.requestAlarmsData(this.userID,this.type);
  }

  clearCurrentAlarmRecordingInfo(){
    this.currentRecordingInfo = '';
  }
  // 备注记录告警信息
  currentAlarmRecordingInfo(){
    console.log(this.currentRecordingInfo);
  }
  curentAlarmOnKey(event:any){
    this.currentRecordingInfo = event.target.value;
  }
  // without strong typing
  onKey(event:any) {
    this.recordingInfo = event.target.value;
  }
  clickalarmList(cClickAlarmInfo:AlarmInfo){
    this.currentClickAlarmInfo = cClickAlarmInfo;
  }
  confirm(clickButtonType:string){
    console.log(clickButtonType);
    console.log(this.recordingInfo);
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
      this.requestAlarmsData(this.userID,this.type);
    }, 5000);
    this.freshTimersInfo.set('my-alarms', freshTimer);
    console.log("my-alarms 定时器启动");
  }
  private requestAlarmsData(userID:string,type:string) {
    console.log(this.recordingInfo);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    // let type:string = this.curType;
    this._http.request(MockCfg.baseUrl + MockCfg.alarmsInfoUrl,<RequestOptionsArgs> {headers: headers, withCredentials: true})
      .subscribe((res:Response) => {
        let datas:any = _.filter(res.json().alarmsInfo,function(data:AlarmInfo){
          console.log(data.alarmStatue);
          return data.alarmStatue.indexOf(type) != -1;
        });
        this.alarmsInfo = datas;
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
