import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { APP_BASE_HREF } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule,ReactiveFormsModule  }   from '@angular/forms';
import { AppComponent } from './app.component';
import { routes } from './app.routes';

import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { HeaderMenuModule,HeaderRegisterModule,HeaderLoginModule } from './header/index';

import { SharedModule } from './shared/shared.module';

import { Ng2BootstrapModule } from 'ng2-bootstrap/ng2-bootstrap';
import { BodyComponent } from './body/index';

import {
  MyAlarmsModule,
  MyAlarmsActiveModule,
  MyAlarmsAckModule,
  MyAlarmsForwardedModule,
  MyAlarmsClosedModule,
  AllAlarmsModule,
  AllAlarmsAckModule,
  AllAlarmsActiveModule,
  AllAlarmsClosedModule
} from './alarmInfo/index'

import {
  AllocationStrategyModule,
  DeployAppModule,
  NotificationPolicyModule,
  SchedulingModule,
} from './configure/index'

import {
  AnalysisAppModule,
  AnalysisMemberModule,
  AnalysisNoticeModule,
  AnalysisTeamModule,
  OverviewModule
} from './analysis/index'

import {
  HelpFileModule,
  HelpForumModule,
  NoviceGuideModule
} from './help/index'

import {
  AuthorizeModule,
  PersonalCenterModule,
  TeamUserModule
} from './team/index'


@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule,
    Ng2BootstrapModule,
    HeaderMenuModule,
    HeaderRegisterModule,
    HeaderLoginModule,

    MyAlarmsModule,
    MyAlarmsActiveModule,
    MyAlarmsAckModule,
    MyAlarmsForwardedModule,
    MyAlarmsClosedModule,
    AllAlarmsModule,
    AllAlarmsAckModule,
    AllAlarmsActiveModule,
    AllAlarmsClosedModule,

    AllocationStrategyModule,
    DeployAppModule,
    NotificationPolicyModule,
    SchedulingModule,

    AnalysisAppModule,
    AnalysisMemberModule,
    AnalysisNoticeModule,
    AnalysisTeamModule,
    OverviewModule,

    HelpFileModule,
    HelpForumModule,
    NoviceGuideModule,

    AuthorizeModule,
    PersonalCenterModule,
    TeamUserModule,

    ReactiveFormsModule,
    SharedModule.forRoot()
    ],
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    BodyComponent
    ],
  providers: [{
    provide: APP_BASE_HREF,
    useValue: '<%= APP_BASE %>'
  }],
  bootstrap: [AppComponent]

})

export class AppModule { }
