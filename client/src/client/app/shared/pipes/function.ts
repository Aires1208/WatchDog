import { Injectable } from '@angular/core';
@Injectable()

export class DateFormat {


  IsoLocalTime(inputIntTime: any) {
    if (!inputIntTime) {
      return null;
    }
    if (!isNaN(inputIntTime)) {
      inputIntTime = inputIntTime;
    }else {
      inputIntTime = Date.parse(inputIntTime);
    }
    let localTime: string = '';
    let offset: number = (new Date()).getTimezoneOffset();
    localTime = (new Date(inputIntTime - offset * 60000)).toISOString();
    localTime = localTime.substr(0, localTime.lastIndexOf('Z'));
    localTime = localTime.replace('T', ' ');
    return localTime;
  }
  LocalIsoTime(inputIntTime: any) {
    if (!inputIntTime) {
      return null;
    }
    inputIntTime = Date.parse(inputIntTime);
    let IsoTime: string = '';
    //let offset: number = (new Date()).getTimezoneOffset();
    IsoTime = (new Date(inputIntTime)).toISOString();
    return IsoTime;
  }
}
