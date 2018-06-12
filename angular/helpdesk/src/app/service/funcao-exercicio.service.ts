import { HttpClient } from '@angular/common/http';
import { FuncaoExercicio } from './../model/funcao-exercicio.model';
import {FYD_API } from './fyd.api';
import { Injectable } from '@angular/core';

@Injectable()
export class FuncaoExercicioService {

  constructor(private http:HttpClient) { }

  createOrUpdate(funcaoExercicio: FuncaoExercicio){
    if(funcaoExercicio.id != null && funcaoExercicio.id != ""){
      return this.http.put(`${FYD_API}/api/funcaoExercicio`, funcaoExercicio);
    }
    else{
      funcaoExercicio.id = null;
      return this.http.post(`${FYD_API}/api/funcaoExercicio`, funcaoExercicio);
    }
  }

  findAll(page:number, count:number){
    return this.http.get(`${FYD_API}/api/funcaoExercicio/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/funcaoExercicio/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/funcaoExercicio/${id}`);
  }
}
