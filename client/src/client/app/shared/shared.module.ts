import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { MaterialModule } from './material.module';
import { UserService } from './services/user/user.service';
import { EventHandleModule } from './event-notification/event-handle.component.module';
import { EventBroadcastService } from './event-notification/event-broadcast.component';
import { HttpService } from './http/index';
import {LocalStorageService} from './localstorage.service';



/**
 * Do not specify providers for modules that might be imported by a lazy loaded module.
 */

@NgModule({
  imports: [EventHandleModule,CommonModule, RouterModule, MaterialModule.forRoot()],
  exports: [EventHandleModule,MaterialModule,CommonModule, FormsModule, RouterModule]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [UserService,EventBroadcastService,HttpService,LocalStorageService]
    };
  }
}
