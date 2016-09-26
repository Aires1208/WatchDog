import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyAlarmsComponent } from './index';

@NgModule({
  imports: [CommonModule],
  declarations: [MyAlarmsComponent],
  exports: [MyAlarmsComponent]
})

export class MyAlarmsModule { }
