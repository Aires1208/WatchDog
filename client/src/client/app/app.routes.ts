import { Route  } from '@angular/router';
import { LoginComponent} from './login/index';
import { RegisterComponent } from './register/index';
import { BodyComponent } from './body/index';
import {
  MyAlarmsClosedComponent,
  MyAlarmsForwardedComponent,
  MyAlarmsComponent,
  MyAlarmsActiveComponent,
  MyAlarmsAckComponent,
  AllAlarmsComponent,
  AllAlarmsAckComponent,
  AllAlarmsActiveComponent,
  AllAlarmsClosedComponent
} from './alarmInfo/index'

import {
  AllocationStrategyComponent,
  DeployAppComponent,
  NotificationPolicyComponent,
  SchedulingComponent
} from './configure/index'


import {
  AnalysisAppComponent,
  AnalysisMemberComponent,
  AnalysisNoticeComponent,
  AnalysisTeamComponent,
  OverviewComponent
} from './analysis/index'

import {
  HelpFileComponent,
  HelpForumComponent,
  NoviceGuideComponent
} from './help/index'

import {
  AuthorizeComponent,
  PersonalCenterComponent,
  TeamUserComponent
} from './team/index'

export const routes: Route[] = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch:'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path:'body',
    component: BodyComponent,
    children:[
      {
        path:'',
        redirectTo:'/body/myAlarmsActive',
        pathMatch:'full'
      },
      {
        path:'myAlarmsActive',
        component:MyAlarmsActiveComponent
      },
      {
        path:'myAlarms',
        component:MyAlarmsComponent
      },
      {
        path:'myAlarmsAck',
        component:MyAlarmsAckComponent
      },
      {
        path:'myAlarmsForwarded',
        component:MyAlarmsForwardedComponent
      },
      {
        path:'myAlarmsClosed',
        component:MyAlarmsClosedComponent
      },
      {
        path:'allAlarms',
        component:AllAlarmsComponent
      },
      {
        path:'allAlarmsActive',
        component:AllAlarmsActiveComponent
      },
      {
        path:'allAlarmsAck',
        component:AllAlarmsAckComponent
      },
      {
        path:'allAlarmsClosed',
        component:AllAlarmsClosedComponent
      },
      {
        path:'allocationStrategy',
        component:AllocationStrategyComponent
      },
      {
        path:'deployApp',
        component:DeployAppComponent
      },
      {
        path:'notificationPolicy',
        component:NotificationPolicyComponent
      },
      {
        path:'scheduling',
        component:SchedulingComponent
      },
      {
        path:'analysisApp',
        component:AnalysisAppComponent
      },
      {
        path:'analysisMember',
        component:AnalysisMemberComponent
      },
      {
        path:'analysisNotice',
        component:AnalysisNoticeComponent
      },
      {
        path:'analysisTeam',
        component:AnalysisTeamComponent
      },
      {
        path:'overview',
        component:OverviewComponent
      },
      {
        path:'helpFile',
        component:HelpFileComponent
      },
      {
        path:'helpForum',
        component:HelpForumComponent
      },
      {
        path:'noviceGuide',
        component:NoviceGuideComponent
      },
      {
        path:'authorize',
        component:AuthorizeComponent
      },
      {
        path:'personalCenter',
        component:PersonalCenterComponent
      },
      {
        path:'teamUser',
        component:TeamUserComponent
      }
    ]
  }
];
