import { User } from './../model/user.model';
import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class SharedService {

  public static instance: SharedService = null;
  user: User;
  token: string;
  showTemplate = new EventEmitter<boolean>();

  constructor() { 
    return SharedService.instance = SharedService.instance || this;
  }

  public static getInstance(){
    if(this.instance == null){
      this.instance = new SharedService();
    }

    if(localStorage.getItem('currentUser') && localStorage.getItem('currentToken')){
      this.instance.token = localStorage.getItem('currentToken');
      this.instance.user = JSON.parse(localStorage.getItem('currentUser'));
      this.instance.showTemplate.emit(true);
    }

    return this.instance;
  }

  public static emptyInstance(){
    this.instance = null;
  }

  isLoggedIn():boolean{
    if(this.user == null){
      return false;
    }
    return this.user.email != '';
  }

}
