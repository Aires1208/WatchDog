import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyAlarmsActiveComponent } from './index';

@NgModule({
  imports: [CommonModule],
  declarations: [MyAlarmsActiveComponent],
  exports: [MyAlarmsActiveComponent]
})

export class MyAlarmsActiveModule { }
