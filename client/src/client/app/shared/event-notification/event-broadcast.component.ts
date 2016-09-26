/**
 * Created by zte on 16-7-16.
 */
import { EventEmitter } from '@angular/core';
export class EventBroadcastService {
    public eventBroadcast: EventEmitter<any>;

    constructor() {
        this.eventBroadcast = new EventEmitter();
    }

    public broadcastSuccess(msgData: any): void {
        this.eventBroadcast.emit({msg:'success', msgData:msgData});
    }
    public broadcastError(msgData: any): void {
        this.eventBroadcast.emit({msg:'error', msgData:msgData});
    }
}
