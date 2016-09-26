import { Pipe, PipeTransform } from '@angular/core';
@Pipe({ name: 'LogISOLocalPipe' })
export class LogISOLocalPipe implements PipeTransform {
  transform(value:any) {
    if (!value) {
      return null;
    }
    if (!isNaN(value)) {
      value = value;
    }else {
      value = Date.parse(value);
    }
    let localTime: string = '';
    let offset: number = (new Date()).getTimezoneOffset();
    localTime = (new Date(value - offset * 60000)).toISOString();
    localTime = localTime.substr(0, localTime.lastIndexOf('Z'));
    localTime = localTime.replace('T', ' ');
    return localTime;
  }
}
