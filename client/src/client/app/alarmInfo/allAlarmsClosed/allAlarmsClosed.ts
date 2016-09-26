import {Component,ViewChild} from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { MockCfg } from '../../mock';
import { AlarmInfo } from '../../shared/index';
import {Ng2Bs3ModalModule,ModalComponent} from 'ng2-bs3-modal/ng2-bs3-modal';
var _ = require('underscore');

@Component({
  moduleId: module.id,
  selector:'allAlarms-closed',
  templateUrl:'../alarmInfo.html',
  styleUrls:['../alarmInfo.css']
})
export class AllAlarmsClosedComponent {
  @ViewChild('modal')
  modal:ModalComponent;
  alarmType:string;
  curType:string;
  constructor(private _http: Http) {
    this.alarmType = "所有告警";
    this.curType = "已关闭";

  }
}

