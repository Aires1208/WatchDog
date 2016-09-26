import {Directive, ElementRef,Component,Input,Output} from '@angular/core';
import { UserService } from '../shared/services/user/user.service';
import {Router} from '@angular/router';
@Directive({ selector: '[autoFocus]' })
class AutoFocusDirective {
  private el: HTMLElement;
  constructor(el: ElementRef) {
    this.el = el.nativeElement;
  }
  ngAfterContentInit(){
    this.el.focus();
  }
}
@Component({
    moduleId: module.id,
    selector:'header-menu',
    templateUrl:'header-menu.component.html',
    styleUrls:['header-menu.component.css']
})

export class HeaderMenuComponent {
    @Input() isCentered: boolean = false;

    private projects:Array<any>;
    private userName:string;
    private userId:string;
    private activeProject:string;
    sidbarTypeArray:Array = ["alarm","app","team","analysis","help"];

    constructor(private _userService: UserService,private _router: Router) {
      this._router.navigateByUrl(this.myAlarmsActiveLink);
    }
    isOn:Array = [true,false,false,false,false];

    changeHeaderMenu(type:string){
      this.isOn = [false,false,false,false,false];
      let i:number = 0;
      for(i=0;i<this.sidbarTypeArray.length;i++){
        if(this.sidbarTypeArray[i] === type){
          this.isOn[i] = true;
          this._userService.headerSidbarType = this.sidbarTypeArray[i];
          console.log(this._userService.headerSidbarType);
        }
      }
    }
    myAlarmsActiveLink:string = "/body/myAlarmsActive";
    myAlarmsActive(type:string) {
      this._router.navigateByUrl(this.myAlarmsActiveLink);
      this.changeHeaderMenu(type);
    }
    deployAppLink:string = "/body/deployApp";
    deployApp(type:string) {
      this._router.navigateByUrl(this.deployAppLink);
      this.changeHeaderMenu(type);
    }
    allocationStrategyLink:string = "/body/allocationStrategy";
    allocationStrategy(type:string) {
      this._router.navigateByUrl(this.allocationStrategyLink);
      this.changeHeaderMenu(type);
    }
    schedulingLink:string = "/body/scheduling";
    scheduling(type:string){
      this._router.navigateByUrl(this.schedulingLink);
      this.changeHeaderMenu(type);
    }
    notificationPolicyLink:string = "/body/notificationPolicy";
    notificationPolicy(type:string){
      this._router.navigateByUrl(this.notificationPolicyLink);
      this.changeHeaderMenu(type);
    }

    personalCenterLink:string = "/body/personalCenter";
    personalCenter(type:string){
      this._router.navigateByUrl(this.personalCenterLink);
      this.changeHeaderMenu(type);
    }
    teamUserLink:string = "/body/teamUser";
    teamUser(type:string){
      this._router.navigateByUrl(this.teamUserLink);
      this.changeHeaderMenu(type);
    }
  authorizeLink:string = "/body/authorize";
  authorize(type:string){
    this._router.navigateByUrl(this.authorizeLink);
    this.changeHeaderMenu(type);
  }
  overviewLink:string = "/body/overview";
  overview(type:string){
    this._router.navigateByUrl(this.overviewLink);
    this.changeHeaderMenu(type);
  }
  analysisAppLink:string = "/body/analysisApp";
  analysisApp(type:string){
    this._router.navigateByUrl(this.analysisAppLink);
    this.changeHeaderMenu(type);
  }
  analysisTeamLink:string = "/body/analysisTeam";
  analysisTeam(type:string){
    this._router.navigateByUrl(this.analysisTeamLink);
    this.changeHeaderMenu(type);
  }
  analysisMemberLink:string = "/body/analysisMember";
  analysisMember(type:string){
    this._router.navigateByUrl(this.analysisMemberLink);
    this.changeHeaderMenu(type);
  }
  analysisNoticeLink:string = "/body/analysisNotice";
  analysisNotice(type:string){
    this._router.navigateByUrl(this.analysisNoticeLink);
    this.changeHeaderMenu(type);
  }
  helpFileLink:string = "/body/helpFile";
  helpFile(type:string){
    this._router.navigateByUrl(this.helpFileLink);
    this.changeHeaderMenu(type);
  }
  helpForumLink:string = "/body/helpForum";
  helpForum(type:string){
    this._router.navigateByUrl(this.helpForumLink);
    this.changeHeaderMenu(type);
  }
  noviceGuideLink:string = "/body/noviceGuide";
  noviceGuide(type:string){
    this._router.navigateByUrl(this.noviceGuideLink);
    this.changeHeaderMenu(type);
  }


    ngOnInit(){

    }
    logout() {

    }
    getUserInfo() {

    }
}

