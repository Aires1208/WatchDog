import { Injectable } from '@angular/core';
import { Headers, Http, Response} from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { LocalStorageService } from '../localstorage.service';



@Injectable()
export class HttpService {
    private requestOptions:any;
    constructor(private _http: Http) {
        let localStorageService=new LocalStorageService();
        this.requestOptions = {
            headers:new Headers({
                'Content-Type': 'application/json',
                'Access-Token':  localStorageService.get('Access-Token')})
        };
    }


    handleError (error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        console.log(error);
        if(error.message) {
            return Observable.throw({message:error.message,status:error.status});
        }

        let message :string= null;
        if(error._body) {
            try{
                let body = JSON.parse(error._body);
                message = body.message||body.error;
            }catch(e){;}
        }
        let errMsg = (message) ? message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw({message:errMsg,status:error.status});
    }

    post(url:string,data:any,isRspJson:boolean = true) {
        let that = this;
        return that._http.post(url,JSON.stringify(data),this.requestOptions)
            .map(rsp => {
                if(isRspJson)
                    return rsp.json();
            })
            .catch(that.handleError);
    }

    delete(url:string,isRspJson:boolean = true) {
        let that = this;
        return that._http.delete(url,this.requestOptions)
            .map(rsp => {
                if(rsp.status===204){
                    return;
                }
                if(isRspJson) return rsp.json();
            })
            .catch(that.handleError);
    }

    put(url:string,data:any,isRspJson:boolean = true) {
        let that = this;
        return that._http.put(url,JSON.stringify(data),this.requestOptions)
            .map(rsp => {if(isRspJson) return rsp.json();})
            .catch(that.handleError);
    }

    get(url:string,isRspJson:boolean = true) {
        let that = this;
        return that._http.get(url,this.requestOptions)
            .map(rsp => {if(isRspJson) return rsp.json();})
            .catch(that.handleError);
    }

    uploadJsonFile(uploadTo: string, filename: string, data: JSON) {
        let that = this;
        let bound = '----WebKitFormBoundaryUCFUq6ZUcgljFsfb';
        let headers = new Headers({
            'Content-Type': 'multipart/form-data;boundary=' + bound
        });
        let reqbodyDetail = '------WebKitFormBoundaryUCFUq6ZUcgljFsfb\r\nContent-Disposition: form-data; name="file"; filename="'
            + filename + '"\r\nContent-Type: text/plain\r\n\r\n' + JSON.stringify(data) + '\r\n------WebKitFormBoundaryUCFUq6ZUcgljFsfb--\r\n'

        return that._http.post(uploadTo, reqbodyDetail, { headers: headers })
            .map(res => { })
            .catch(that.handleError);
    }

}
