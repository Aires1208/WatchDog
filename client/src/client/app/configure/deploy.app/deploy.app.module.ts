import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeployAppComponent } from './index';

@NgModule({
  imports: [CommonModule],
  declarations: [DeployAppComponent],
  exports: [DeployAppComponent]
})

export class DeployAppModule { }
