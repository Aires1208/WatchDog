import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyAlarmsForwardedComponent } from './index';

@NgModule({
  imports: [CommonModule],
  declarations: [MyAlarmsForwardedComponent],
  exports: [MyAlarmsForwardedComponent]
})

export class MyAlarmsForwardedModule { }
