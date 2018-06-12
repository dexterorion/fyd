import { FYD_API } from './fyd.api';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.model';


@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  login(user: User){
    return this.http.post(`${FYD_API}/api/auth`,user);
  }

  createOrUpdate(user: User){
    if(user.id != null && user.id != ""){
      return this.http.put(`${FYD_API}/api/user`, user);
    }
    else{
      user.id = null;
      return this.http.post(`${FYD_API}/api/user`, user);
    }
  }

  findAll(page:number, count: number){
    return this.http.get(`${FYD_API}/api/user/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/user/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/user/${id}`);
  }
}
