import { FYD_API } from './fyd.api';
import { ModalidadeTreino } from './../model/modalidade-treino.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class ModalidadeTreinoService {

  constructor(private http:HttpClient) { }

  createOrUpdate(modalidadeTreino: ModalidadeTreino){
    if(modalidadeTreino.id != null && modalidadeTreino.id != ""){
      return this.http.put(`${FYD_API}/api/modalidadeTreino`, modalidadeTreino);
    }
    else{
      modalidadeTreino.id = null;
      return this.http.post(`${FYD_API}/api/modalidadeTreino`, modalidadeTreino);
    }
  }

  findAll(page:number, count:number){
    return this.http.get(`${FYD_API}/api/modalidadeTreino/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${FYD_API}/api/modalidadeTreino/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${FYD_API}/api/modalidadeTreino/${id}`);
  }
}
