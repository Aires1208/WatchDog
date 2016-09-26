import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventHandleComponent } from './event-handle.component';

@NgModule({
    imports: [CommonModule],
    declarations: [EventHandleComponent],
    exports: [EventHandleComponent]
})

export class EventHandleModule { }
