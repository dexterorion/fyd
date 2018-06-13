import { FYD_API } from './fyd.api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TipoTreino } from '../model/tipo-treino.model';

@Injectable()
export class TipoTreinoService {

  constructor(private http:HttpClient) { }

  createOrUpdate(tipoTreino: TipoTreino){
    if(tipoTreino.id != null && tipoTreino.id != ""){
      return this.http.put(`${FYD_API}/api/tipoTreino`, tipoTreino);
    }
    else{
      tipoTreino.id = null;
      return this.http.post(`${FYD_API}/api/tipoTreino`, tipoTreino);
    }
  }

  findAll(page:number, count:number){
    return this.http.get(`${FYD_API}/api/tipoTreino/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/tipoTreino/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/tipoTreino/${id}`);
  }
}
