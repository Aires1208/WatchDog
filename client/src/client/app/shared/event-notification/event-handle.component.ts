/**
 * Created by zte on 16-7-18.
 */
import { Component } from '@angular/core';
import { EventBroadcastService } from './event-broadcast.component';

@Component({
    selector: 'event-handle',
    template: `
    <div class="alert global-alert" role="alert" [ngClass]="{'alertSuccess':eventMsg.msg=='success','alertError':eventMsg.msg=='error'}" *ngIf="showalert">
      <img src="../../images/icon/right.png" *ngIf="eventMsg.msg=='success'"/>
      <img src="../../images/icon/error.png" *ngIf="eventMsg.msg=='error'"/>
      <strong>{{eventMsg.msg|uppercase}}:</strong> {{eventMsg.msgData}}
    </div>
  `,
    styles:[`
    .global-alert{
      position: fixed;
      z-index: 1002;
      top: 0;
      /*Firefox*/
      left:-moz-calc((100% - 500px)/2);
      /*chrome safari*/
      left:-webkit-calc((100% - 500px)/2);
      /*Standard */
      left: calc((100% - 500px)/2);
      width: 500px;
      text-align: center;
      border-top-left-radius: 0px;
      border-top-right-radius: 0px;
      color:#fff;
      background-color: #5cb85c;
      box-shadow: 0px 1px 5px #ddd;
    }
    .alertError{
      background-color: #f76661;
    }
    .alertSuccess{
      background-color: #5cb85c;
    }
    img{
      margin-right: 10px;
    }
  `]
})

export class EventHandleComponent {
    showalert:boolean = false;
    eventMsg:any;
    constructor(eventBroadcastService: EventBroadcastService) {
        eventBroadcastService.eventBroadcast.subscribe(msgData => this.onEvent(msgData));
    }
    alertTime:any = undefined;

    private showAlert(msgData:any): void {
        if(this.alertTime){
            clearTimeout(this.alertTime);
            this.showalert = false;
        }
        let that = this;
        that.eventMsg = msgData;
        that.showalert = true;
        that.alertTime = setTimeout(function(){
            that.showalert = false;
        },3000);
    }

    private onEvent(msgData:any): void {
        this.showAlert(msgData);
    }
}
