import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorizeComponent } from './index';

@NgModule({
  imports: [CommonModule],
  declarations: [AuthorizeComponent],
  exports: [AuthorizeComponent]
})

export class AuthorizeModule { }
