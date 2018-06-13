import { FYD_API } from './fyd.api';
import { HttpClient } from '@angular/common/http';
import { NivelExecucao } from './../model/nivel-execucao.model';
import { Injectable } from '@angular/core';

@Injectable()
export class NivelExecucaoService {

  constructor(private http:HttpClient) { }

  createOrUpdate(nivelExecucao: NivelExecucao){
    if(nivelExecucao.id != null && nivelExecucao.id != ""){
      return this.http.put(`${FYD_API}/api/nivelExecucao`, nivelExecucao);
    }
    else{
      nivelExecucao.id = null;
      return this.http.post(`${FYD_API}/api/nivelExecucao`, nivelExecucao);
    }
  }

  findAll(page:number, count:number){
    return this.http.get(`${FYD_API}/api/nivelExecucao/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/nivelExecucao/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/nivelExecucao/${id}`);
  }
}
