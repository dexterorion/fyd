import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { SharedService } from './../../service/shared.service';
import { HttpInterceptor, HttpRequest, HttpHandler,  HttpResponse, HttpEvent} from "@angular/common/http";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    shared: SharedService;

    constructor(){
        this.shared = SharedService.getInstance();
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        let authRequest : any;

        if(this.shared.isLoggedIn()){
            authRequest = req.clone({
                setHeaders: {
                    'Authorization': this.shared.token
                }
            });
            return next.handle(authRequest);
        }
        else{
            return next.handle(req);
        }
    }
}