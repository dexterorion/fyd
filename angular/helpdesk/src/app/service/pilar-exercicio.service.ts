import { HttpClient } from '@angular/common/http';
import { PilarExercicio } from './../model/pilar-exercicio.model';
import {FYD_API } from './fyd.api';
import { Injectable } from '@angular/core';

@Injectable()
export class PilarExercicioService {

  constructor(private http:HttpClient) { }

  createOrUpdate(pilarExercicio: PilarExercicio){
    if(pilarExercicio.id != null && pilarExercicio.id != ""){
      return this.http.put(`${FYD_API}/api/pilarExercicio`, pilarExercicio);
    }
    else{
      pilarExercicio.id = null;
      return this.http.post(`${FYD_API}/api/pilarExercicio`, pilarExercicio);
    }
  }

  findAll(page:number, count:number){
    return this.http.get(`${FYD_API}/api/pilarExercicio/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/pilarExercicio/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/pilarExercicio/${id}`);
  }
}
