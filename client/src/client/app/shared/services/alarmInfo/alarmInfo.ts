import { HistoryProcessingMember } from './historyProcessingMember'
import { Recording } from './recording'
export interface AlarmInfo {
  // 告警单号
  alarmID: number,
  // 告警对象
  alarmObject: string,
  // 告警内容
  alarmContent: string,
  //所属应用
  belongApp: string,
  // 告警级别
  alarmLevel: string,
  // 告警状态
  alarmStatue: string,
  //上报时间
  activeTime:string,
  //当前处理人
  processingMember:string,
  // 当前处理人处理时间
  processingTime:string,
  historyProcessingMember:Array<HistoryProcessingMember>,
  // 记录
  recording:Array<Recording>
}
