import { Injectable } from '@angular/core';

@Injectable()
export class LocalStorageService {
    constructor(){};
    private storage:any = window.localStorage;

    set(key:string,value:any) {
        this.storage.setItem(key,value);
        /*this.storage.setItem('id',userinfo.id);
         this.storage.setItem('accesstoken',userinfo.accesstoken);
         this.storage.setItem('name',userinfo.name);*/
    }

    get(key:string) {
        let value = this.storage.getItem(key)||undefined;
        /*let userInfo:{id:string,accesstoken:string,name:string};
         userInfo.id=this.storage.getItem("id")||undefined;
         userInfo.accesstoken=this.storage.getItem("accesstoken")||undefined;
         userInfo.name=this.storage.getItem("name")||undefined;*/
        return value;
    }
    clear(key:string) {
        this.storage.removeItem(key);
    }
    clearAllData() {
        this.storage.clear();
    }
}
